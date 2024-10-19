package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetAppointmentsWhichTrucksAreInsideUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultGetAppointmentsWhichTrucksAreInside implements GetAppointmentsWhichTrucksAreInsideUseCase {

    private final LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort;

    public DefaultGetAppointmentsWhichTrucksAreInside(LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort) {
        this.loadOrCreateDailyCalendarPort = loadOrCreateDailyCalendarPort;
    }


    @Override
    public List<Appointment> getAppointmentsWhichTrucksAreInside() {
        DailyCalendar dailyCalendar = loadOrCreateDailyCalendarPort.loadOrCreateDailyCalendarByDay(LocalDate.now());
        return dailyCalendar.getTrucksThatAreInside();
    }
}