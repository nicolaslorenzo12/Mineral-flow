package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadOrCreateDailyCalendarPort {
    DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate);
}
