package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetTrucksThatAreInsideUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LandsideInfoController {

    private final GetTrucksThatAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase;

    public LandsideInfoController(GetTrucksThatAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase) {
        this.checkHowManyTrucksThereAreInsideUseCase = checkHowManyTrucksThereAreInsideUseCase;
    }

    @GetMapping("trucks-inside")
    public ResponseEntity<List<TruckDto>> howManyTrucksThereAreInside() {
        List<AbstractMap.SimpleEntry<Appointment, String>> appointmentsWithMaterial =
                checkHowManyTrucksThereAreInsideUseCase.getTrucksThatAreInside();

        List<TruckDto> truckDtoList = createTruckDtoList(appointmentsWithMaterial);

        return ResponseEntity.ok(truckDtoList);
    }

    private List<TruckDto> createTruckDtoList(List<AbstractMap.SimpleEntry<Appointment, String>> appointmentsWithMaterial) {
        List<TruckDto> truckDtoList = new ArrayList<>();

        appointmentsWithMaterial.forEach(entry -> {
            Appointment appointment = entry.getKey();
            String materialDescription = entry.getValue();

         addTruckDtoToTruckDtoList(appointment, truckDtoList, materialDescription);
        });

        return truckDtoList;
    }

    private void addTruckDtoToTruckDtoList(Appointment appointment, List<TruckDto> truckDtoList, String materialDescription) {

        TruckDto truckDto = new TruckDto(
                appointment.getLicensePlateNumberOfTruck(),
                appointment.getTruckStatus(),
                appointment.getArrivalTime(),
                materialDescription
        );
        truckDtoList.add(truckDto);
    }
}
