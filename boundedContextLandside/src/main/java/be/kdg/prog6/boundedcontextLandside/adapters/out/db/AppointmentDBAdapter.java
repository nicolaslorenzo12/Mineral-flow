package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AppointmentDBAdapter implements LoadAndCreateAppointmentPort {

    private final AppointmentRepository appointmentRepository;
    private final DailyCalendarRepository dailyCalendarRepository;

    public AppointmentDBAdapter(AppointmentRepository appointmentRepository, DailyCalendarRepository dailyCalendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck) {
        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.findAppointmentJpaEntityByLicensePlateNumberOfTruck(licensePlateNumberOfTruck).
         orElseThrow(() -> new RuntimeException("Appointment not found"));

        return Optional.of(buildAppointmentObject(appointmentJpaEntity));
    }

    @Override
    public Optional<List<Appointment>> loadAppointmentsByDailyCalendarJpaEntityAndTime(DailyCalendarJpaEntity dailyCalendarJpaEntity, LocalDateTime localDateTime) {
        List<AppointmentJpaEntity> appointmentJpaEntityList = appointmentRepository.findAppointmentJpaEntityByDailyCalendarJpaEntity(dailyCalendarJpaEntity).orElseThrow();

        List<AppointmentJpaEntity> filteredAppointmentsJPaEntityList = appointmentJpaEntityList.stream()
                .filter(appointment -> isSameHour(appointment.getAppointmentTime(), localDateTime)).toList();

        List<Appointment> appointments = new ArrayList<>();

        return Optional.of(toAppointment(dailyCalendarJpaEntity, filteredAppointmentsJPaEntityList, appointments));
    }

    private boolean isSameHour(LocalDateTime appointmentTime, LocalDateTime now) {
        return appointmentTime.getHour() == now.getHour();
    }

    private List<Appointment> toAppointment(DailyCalendarJpaEntity dailyCalendarJpaEntity, List<AppointmentJpaEntity> appointmentJpaEntityList, List<Appointment> appointments){
        appointmentJpaEntityList.forEach(appointmentJpaEntity -> appointments.add(new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Customer.CustomerUUID(appointmentJpaEntity.getSellerUuid()), appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(), appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(), appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getStatus(), appointmentJpaEntity.getWarehouseNumber())));
        return appointments;
    }
    @Override
    public void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity) {

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getStatus(),
                appointment.getWarehouseNumber(), dailyCalendarJpaEntity);

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity){

        return new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()), new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(), appointmentJpaEntity.getGateNumber(), appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(), appointmentJpaEntity.getWarehouseNumber());
    }
}
