package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.CheckHowManyTrucksThereAreInsideUseCase;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LandsideInfoController {

    private final CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase;

    public LandsideInfoController(CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase) {
        this.checkHowManyTrucksThereAreInsideUseCase = checkHowManyTrucksThereAreInsideUseCase;
    }

    @GetMapping("trucks-inside")
    public ResponseEntity<List<TruckDto>> howManyTrucksThereAreInside() {
        List<AbstractMap.SimpleEntry<Appointment, String>> appointmentsWithMaterial =
                checkHowManyTrucksThereAreInsideUseCase.checkHowManyTrucksThereAreInside();

        List<TruckDto> truckDtoList = createTruckDtoList(appointmentsWithMaterial);

        return ResponseEntity.ok(truckDtoList);
    }

    private List<TruckDto> createTruckDtoList(List<AbstractMap.SimpleEntry<Appointment, String>> appointmentsWithMaterial) {
        List<TruckDto> truckDtoList = new ArrayList<>();

        appointmentsWithMaterial.forEach(entry -> {
            Appointment appointment = entry.getKey();
            String materialDescription = entry.getValue();

            TruckDto truckDto = new TruckDto(
                    appointment.getLicensePlateNumberOfTruck(),
                    appointment.getTruckStatus(),
                    appointment.getArrivalTime(),
                    materialDescription
            );
            truckDtoList.add(truckDto);
        });

        return truckDtoList;
    }
}
