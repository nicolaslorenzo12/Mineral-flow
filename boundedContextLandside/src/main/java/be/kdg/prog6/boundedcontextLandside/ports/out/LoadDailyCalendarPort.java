package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;

import java.time.LocalDate;
import java.util.Optional;

public interface LoadDailyCalendarPort {

    Optional<DailyCalendar> loadDailyCalendarByDay(LocalDate localDate);
}
