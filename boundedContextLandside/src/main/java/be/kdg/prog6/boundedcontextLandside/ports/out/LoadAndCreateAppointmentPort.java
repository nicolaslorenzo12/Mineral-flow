package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.AppointmentJpaEntity;
import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoadAndCreateAppointmentPort {

    Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime appointmentTime, LocalDate day);
    Optional<List<Appointment>> loadAppointmentsByAppointmentTime(LocalDateTime appointmentTime);
    void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity);

    AppointmentJpaEntity loadAppointmentJpaEntityByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID);

    Appointment loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID);
}
