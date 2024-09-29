package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.ObjectNotFoundException;
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
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(
            String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day) {

        return appointmentRepository
                .findAppointmentJpaEntityByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(licensePlateNumberOfTruck, localDateTime, day)
                .map(this::buildAppointmentObject);
    }


    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity) {
        return new Appointment(
                new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(),
                appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(),
                appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getTruckStatus(),
                appointmentJpaEntity.getWarehouseNumber()
        );
    }

    @Override
    public Optional<List<Appointment>> loadAppointmentsByAppointmentTime(LocalDateTime appointmentTime) {
        Optional<List<AppointmentJpaEntity>> optionalAppointmentJpaEntities = appointmentRepository.findAppointmentJpaEntityByAppointmentTime(appointmentTime);

        return optionalAppointmentJpaEntities.map(this::toAppointment);
    }

    private List<Appointment> toAppointment(List<AppointmentJpaEntity> appointmentJpaEntityList) {
        List<Appointment> appointments = new ArrayList<>();
        appointmentJpaEntityList.forEach(appointmentJpaEntity -> appointments.add(new Appointment(
                new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Customer.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(),
                appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(),
                appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getTruckStatus(),
                appointmentJpaEntity.getWarehouseNumber()
        )));
        return appointments;
    }

    @Override
    public void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity) {

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getTruckStatus(),
                appointment.getWarehouseNumber(), appointment.getAppointmentTime().toLocalDate());

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    private Appointment toAppointment(AppointmentJpaEntity appointmentJpaEntity) {
        return new Appointment(
                new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Customer.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(),
                appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(),
                appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getTruckStatus(),
                appointmentJpaEntity.getWarehouseNumber()
        );
    }


    @Override
    public AppointmentJpaEntity loadAppointmentJpaEntityByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID) {
        return appointmentRepository.findAppointmentJpaEntityByAppointmentUUID(appointmentUUID.uuid())
                .orElseThrow(() -> new ObjectNotFoundException("No appoinment found"));
    }

    @Override
    public Appointment loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID) {
        return toAppointment(loadAppointmentJpaEntityByAppointmentUUID(appointmentUUID));
    }

    @Override
    public void updateAppointmentTruckStatus(Appointment.AppointmentUUID appointmentUUID, TruckStatus truckStatus) {

        final AppointmentJpaEntity appointmentJpaEntity = loadAppointmentJpaEntityByAppointmentUUID(appointmentUUID);
        appointmentJpaEntity.setStatus(truckStatus);
        appointmentRepository.save(appointmentJpaEntity);
    }

    @Override
    public void updateAppointmentInitialOrFinalWeight(Appointment.AppointmentUUID appointmentUUID, int weight, int weighingCount) {
        final AppointmentJpaEntity appointmentJpaEntity = loadAppointmentJpaEntityByAppointmentUUID(appointmentUUID);

        updateWeight(appointmentJpaEntity, weight, weighingCount);

        appointmentRepository.save(appointmentJpaEntity);
    }

    private void updateWeight(AppointmentJpaEntity appointmentJpaEntity, int weight, int weighingCount) {
        if (weighingCount == 1) {
            appointmentJpaEntity.setInitialWeight(weight);
        } else {
            appointmentJpaEntity.setFinalWeight(weight);
        }
    }
}
