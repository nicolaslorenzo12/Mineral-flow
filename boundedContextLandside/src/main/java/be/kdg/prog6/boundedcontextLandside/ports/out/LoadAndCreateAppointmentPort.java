package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadAndCreateAppointmentPort {

    Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime appointmentTime, LocalDate day);
    void createAppointment(Appointment appointment, DailyCalendar dailyCalendar);
    Optional<Appointment> loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID);

}
