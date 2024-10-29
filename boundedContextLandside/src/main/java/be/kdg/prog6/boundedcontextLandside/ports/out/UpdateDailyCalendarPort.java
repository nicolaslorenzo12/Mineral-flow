package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

public interface UpdateDailyCalendarPort {
    void updateDailyCalendar(DailyCalendar dailyCalendar, Appointment appointment);
}
