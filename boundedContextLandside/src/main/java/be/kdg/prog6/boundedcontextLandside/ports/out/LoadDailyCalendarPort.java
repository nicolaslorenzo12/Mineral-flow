package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadDailyCalendarPort {
    DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate);
    void createAppointment(Appointment appointment,DailyCalendar dailyCalendar);
    Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day);
    Optional<Appointment> loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID, DailyCalendar dailyCalendar);
}
