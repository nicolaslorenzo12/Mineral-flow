package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LandsideInfoController {

    private final GetAppointmentsWhichTrucksAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase;
    private final GetMaterialUseCase getMaterialUseCase;
    private final GetSellerUseCase getSellerUseCase;

    public LandsideInfoController(GetAppointmentsWhichTrucksAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase, GetMaterialUseCase getMaterialUseCase, GetSellerUseCase getSellerUseCase) {
        this.checkHowManyTrucksThereAreInsideUseCase = checkHowManyTrucksThereAreInsideUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
        this.getSellerUseCase = getSellerUseCase;
    }

    @GetMapping("trucks-inside")
    public ResponseEntity<List<TruckDto>> howManyTrucksThereAreInside() {
        List<Appointment> appointmentsWithMaterial =
                checkHowManyTrucksThereAreInsideUseCase.getAppointmentsWhichTrucksAreInside();

        List<TruckDto> truckDtoList = createTruckDtoList(appointmentsWithMaterial);

        return ResponseEntity.ok(truckDtoList);
    }


    private List<TruckDto> createTruckDtoList(List<Appointment> appointments) {

        return appointments.stream()
                .map(appointment -> {

                    Material material = getMaterialUseCase.getMaterial(new GetMaterialCommand(appointment.getMaterialType()));
                    Seller seller = getSellerUseCase.getSellerBySellerUUID(new GetSellerCommand(appointment.getSellerUUID()));

                    return buildTruckDto(appointment, seller, material);
                })
                .collect(Collectors.toList());
    }

    private TruckDto buildTruckDto(Appointment appointment, Seller seller, Material material) {

        return new TruckDto(
                appointment.getWarehouseNumber(),
                seller.getName(),
                appointment.getLicensePlateNumberOfTruck(),
                appointment.getTruckStatus(),
                appointment.getArrivalTime(),
                material.getDescription()
        );
    }

}
