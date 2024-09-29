package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.ObjectNotFoundException;
import be.kdg.prog6.common.exception.ThisTruckStatusWasAlreadyCheckedException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppointmentDBAdapter implements LoadAndCreateAppointmentPort, UpdateAppointmentPort {

    private final AppointmentRepository appointmentRepository;
    private final DailyCalendarRepository dailyCalendarRepository;

    public AppointmentDBAdapter(AppointmentRepository appointmentRepository, DailyCalendarRepository dailyCalendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day) {
        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.
                findAppointmentJpaEntityByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(licensePlateNumberOfTruck, localDateTime, day).
         orElseThrow(() -> new ObjectNotFoundException("This truck does not have an appointment at this time of the day"));

        if(appointmentJpaEntity.getTruckStatus().equals(TruckStatus.ARRIVED)){
            throw new ThisTruckStatusWasAlreadyCheckedException("This truck has already arrived");
        }
        return Optional.of(buildAppointmentObject(appointmentJpaEntity));
    }

    @Override
    public Optional<List<Appointment>> loadAppointmentsByAppointmentTime(LocalDateTime appointmentTime) {

        List<AppointmentJpaEntity> appointmentJpaEntityList = appointmentRepository.findAppointmentJpaEntityByAppointmentTime(appointmentTime).
                orElseThrow(() -> new RuntimeException("No appointments found"));

        List<Appointment> appointments = new ArrayList<>();
        return Optional.of(toAppointment(appointmentJpaEntityList, appointments));
    }

    private List<Appointment> toAppointment(List<AppointmentJpaEntity> appointmentJpaEntityList, List<Appointment> appointments){
        appointmentJpaEntityList.forEach(appointmentJpaEntity -> appointments.add(new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Customer.CustomerUUID(appointmentJpaEntity.getSellerUuid()), appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(), appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(), appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getTruckStatus(), appointmentJpaEntity.getWarehouseNumber())));
        return appointments;
    }
    @Override
    public void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity) {

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getStatus(),
                appointment.getWarehouseNumber(), appointment.getAppointmentTime().toLocalDate());

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity){

        return new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()), new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(), appointmentJpaEntity.getGateNumber(), appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(), appointmentJpaEntity.getWarehouseNumber());
    }

    @Override
    public void updateAppointmentTruckStatus(Appointment appointment, TruckStatus truckStatus) {

        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.findAppointmentJpaEntityByAppointmentUUID(appointment.getAppointmentUUID().uuid()).
                orElseThrow(() -> new RuntimeException("No appointment found"));

        appointmentJpaEntity.setStatus(truckStatus);
        appointmentRepository.save(appointmentJpaEntity);
    }
}
