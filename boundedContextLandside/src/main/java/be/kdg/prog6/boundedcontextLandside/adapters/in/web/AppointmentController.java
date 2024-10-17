package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.CreatedAppointmentDto;
import be.kdg.prog6.boundedcontextLandside.domain.dto.LoadedMaterialDto;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckWeightedDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class AppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;
    private final ScanLicensePlateNumberWhenArrivingUseCase scanLicensePlateNumberOfATruckUseCase;
    private final WeightTruckUseCase weightTruckUseCase;
    private final DeliverMaterialUseCase deliverMaterialUseCase;
    private final GetMaterialUseCase getMaterialUseCase;

    public AppointmentController(MakeAppointmentUseCase makeAppointmentUseCase, ScanLicensePlateNumberWhenArrivingUseCase scanLicensePlateNumberOfATruckUseCase, WeightTruckUseCase weightTruckUseCase, DeliverMaterialUseCase deliverMaterialUseCase, GetMaterialUseCase getMaterialUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
        this.scanLicensePlateNumberOfATruckUseCase = scanLicensePlateNumberOfATruckUseCase;
        this.weightTruckUseCase = weightTruckUseCase;
        this.deliverMaterialUseCase = deliverMaterialUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
    }


    @PostMapping("material-truck-appointment/seller/{sellerUuid}/material/{materialType}/licensePlateNumbe/{licensePlateNumberOfTruck}/appointment/{appointmentTime}")
    public ResponseEntity<CreatedAppointmentDto> makeAppointment(@PathVariable UUID sellerUuid, @PathVariable MaterialType materialType,
                                                  @PathVariable String licensePlateNumberOfTruck, @PathVariable LocalDateTime appointmentTime) {

            CreatedAppointmentDto createdAppointmentDto = makeAppointmentUseCase.makeAppointment(new MakeAppointmentCommand(new Seller.CustomerUUID(sellerUuid), materialType,
                    licensePlateNumberOfTruck, appointmentTime));

            return ResponseEntity.ok(createdAppointmentDto);
    }

    @PostMapping("appointment/truck/{licensePlateNumber}/check")
    public ResponseEntity<TruckDto> scanTruckForAppointment(@PathVariable String licensePlateNumber){

        Appointment appointment = scanLicensePlateNumberOfATruckUseCase.scanLicensePlateNumber(new ScanLicensePlateNumberWhenArrivingCommand(licensePlateNumber));
        Material material = getMaterialUseCase.getMaterial(new GetMaterialCommand(appointment.getMaterialType()));
        TruckDto truckDto = new TruckDto(appointment.getLicensePlateNumberOfTruck(),
                appointment.getTruckStatus(), appointment.getArrivalTime(), material.getDescription());
        return ResponseEntity.ok(truckDto);
    }

    @PostMapping("appointment/{appointmentUuid}/weight")
    public ResponseEntity<TruckWeightedDto> weightTruckDuringAppointment(@PathVariable UUID appointmentUuid){

        TruckWeightedDto truckWeightedDto = weightTruckUseCase.
                weightTruck(new WeightTruckCommand(new Appointment.AppointmentUUID(appointmentUuid)));

        return ResponseEntity.ok(truckWeightedDto);
    }

    @PostMapping("appointment/{appointmentUuid}/loadMaterial")
    public ResponseEntity<LoadedMaterialDto> loadMaterial(@PathVariable UUID appointmentUuid){
        LoadedMaterialDto loadedMaterialDto = deliverMaterialUseCase.deliverMaterial(new DeliverMaterialCommand(new Appointment.AppointmentUUID(appointmentUuid)));
        return ResponseEntity.ok(loadedMaterialDto);
    }
}
