package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.in.CheckHowManyTrucksThereAreInsideUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultCheckHowManyTrucksThereAreInsideUseCase implements CheckHowManyTrucksThereAreInsideUseCase {

    public final LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort;

    public DefaultCheckHowManyTrucksThereAreInsideUseCase(LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort) {
        this.loadOrCreateDailyCalendarPort = loadOrCreateDailyCalendarPort;
    }

    @Override
    public int checkHowManyTrucksThereAreInside(LocalDateTime localDateTime) {

        DailyCalendar dailyCalendar = loadOrCreateDailyCalendarPort.loadOrCreateDailyCalendarByDay(localDateTime.toLocalDate());
        return dailyCalendar.getNumberOfAppointmentsAtACertainHourThatAreInside(LocalDateTime.now());
    }
}
