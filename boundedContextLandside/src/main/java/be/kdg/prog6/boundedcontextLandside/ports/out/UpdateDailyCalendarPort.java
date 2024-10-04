package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

public interface UpdateDailyCalendarPort {

//    void updateAppointment(Appointment appointment, DailyCalendar dailyCalendar);
    void updateDailyCalendar(DailyCalendar dailyCalendar, Appointment appointment);
}
