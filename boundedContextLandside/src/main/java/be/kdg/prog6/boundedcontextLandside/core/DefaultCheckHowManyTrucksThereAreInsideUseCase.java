package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.CheckHowManyTrucksThereAreInsideUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCheckHowManyTrucksThereAreInsideUseCase implements CheckHowManyTrucksThereAreInsideUseCase {

    private final LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort;
    private final LoadMaterialPort loadMaterialPort;

    public DefaultCheckHowManyTrucksThereAreInsideUseCase(LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort, LoadMaterialPort loadMaterialPort) {
        this.loadOrCreateDailyCalendarPort = loadOrCreateDailyCalendarPort;
        this.loadMaterialPort = loadMaterialPort;
    }


    @Override
    public List<AbstractMap.SimpleEntry<Appointment, String>> checkHowManyTrucksThereAreInside() {
        DailyCalendar dailyCalendar = loadOrCreateDailyCalendarPort.loadOrCreateDailyCalendarByDay(LocalDate.now());
        List<Appointment> appointments = dailyCalendar.getTrucksThatAreInside();

        return appointments.stream()
                .map(appointment -> {
                    MaterialType materialType = appointment.getMaterialType();
                    Material material = findMaterialByMaterialType(materialType);
                    return new AbstractMap.SimpleEntry<>(appointment, material.getDescription());
                }).toList();
    }

    private Material findMaterialByMaterialType(MaterialType materialType) {
        return loadMaterialPort.loadMaterialByMaterialType(materialType)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Material not found"));
    }
}