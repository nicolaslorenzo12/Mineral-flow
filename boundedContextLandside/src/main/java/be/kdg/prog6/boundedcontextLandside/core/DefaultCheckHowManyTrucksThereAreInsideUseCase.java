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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultCheckHowManyTrucksThereAreInsideUseCase implements CheckHowManyTrucksThereAreInsideUseCase {

    private final LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort;
    private final LoadMaterialPort loadMaterialPort;

    public DefaultCheckHowManyTrucksThereAreInsideUseCase(LoadOrCreateDailyCalendarPort loadOrCreateDailyCalendarPort, LoadMaterialPort loadMaterialPort) {
        this.loadOrCreateDailyCalendarPort = loadOrCreateDailyCalendarPort;
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public List<TruckDto> checkHowManyTrucksThereAreInside(LocalDateTime localDateTime) {

        DailyCalendar dailyCalendar = loadOrCreateDailyCalendarPort.loadOrCreateDailyCalendarByDay(localDateTime.toLocalDate());
        List<Appointment> appointments= dailyCalendar.getNumberOfAppointmentsAtACertainHourThatAreInside(LocalDateTime.now());
        return createTruckDtoList(appointments);
    }

    private List<TruckDto> createTruckDtoList(List<Appointment> appointments) {

        List<TruckDto> truckDtoList = new ArrayList<>();

        appointments.forEach(appointment -> {

            MaterialType materialType = appointment.getMaterialType();
            Material material = findMaterialByMaterialType(materialType);

         addTruckDtoToTruckDtoList(truckDtoList, appointment, material);
        });

        return truckDtoList;
    }

    private void addTruckDtoToTruckDtoList(List<TruckDto> truckDtoList, Appointment appointment, Material material) {

        truckDtoList.add(new TruckDto(
                appointment.getLicensePlateNumberOfTruck(),
                appointment.getTruckStatus(),
                appointment.getArrivalTime(),
                material != null ? material.getDescription() : "No description available"
        ));
    }

    private Material findMaterialByMaterialType(MaterialType materialType) {
        return loadMaterialPort.loadMaterialByMaterialType(materialType)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Material not found"));
    }
}