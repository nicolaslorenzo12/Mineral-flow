package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

import java.time.LocalDate;

public interface LoadOrCreateDailyCalendarPort {
    DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate);
}
