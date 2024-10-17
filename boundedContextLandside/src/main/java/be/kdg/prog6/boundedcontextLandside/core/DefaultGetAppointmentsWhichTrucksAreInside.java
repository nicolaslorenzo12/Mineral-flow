package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetAppointmentsWhichTrucksAreInsideUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
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