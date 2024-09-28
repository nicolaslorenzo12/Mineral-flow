package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class AppointmentDBAdapter implements LoadAndCreateAppointmentPort {

    private final AppointmentRepository appointmentRepository;
    private final DailyCalendarRepository dailyCalendarRepository;

    public AppointmentDBAdapter(AppointmentRepository appointmentRepository, DailyCalendarRepository dailyCalendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    @Override
    public Appointment loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck) {
        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.findAppointmentJpaEntityByLicensePlateNumberOfTruck(licensePlateNumberOfTruck).
         orElseThrow(() -> new RuntimeException("Appointment not found"));

        return buildAppointmentObject(appointmentJpaEntity);
    }

    @Override
    public void createAppointment(Appointment appointment) {

//        final String licensePlateNumberOfTruck = appointment.getLicensePlateNumberOfTruck();
//        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.findAppointmentJpaEntityByLicensePlateNumberOfTruck(licensePlateNumberOfTruck).orElseThrow();

        LocalDate localDate = appointment.getAppointmentTime().toLocalDate();
        DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(localDate).
                orElseThrow();

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getStatus(),
                appointment.getWarehouseNumber(), dailyCalendarJpaEntity);

        dailyCalendarJpaEntity.getAppointments().add(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity){

        return new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()), new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getGateNumber(), appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(), appointmentJpaEntity.getWarehouseNumber());
    }
}
