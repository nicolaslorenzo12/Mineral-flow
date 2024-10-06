package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.WeightingTime;
import be.kdg.prog6.boundedcontextLandside.domain.dto.CreatedAppointmentDto;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckArrivedDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.*;
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
    public AppointmentController(MakeAppointmentUseCase makeAppointmentUseCase, ScanLicensePlateNumberWhenArrivingUseCase scanLicensePlateNumberOfATruckUseCase, WeightTruckUseCase weightTruckUseCase, DeliverMaterialUseCase deliverMaterialUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
        this.scanLicensePlateNumberOfATruckUseCase = scanLicensePlateNumberOfATruckUseCase;
        this.weightTruckUseCase = weightTruckUseCase;
        this.deliverMaterialUseCase = deliverMaterialUseCase;
    }


    @PostMapping("material-truck-appointment/seller/{sellerUuid}/material/{materialType}/licensePlateNumbe/{licensePlateNumberOfTruck}/appointment/{appointmentTime}")
    public ResponseEntity<CreatedAppointmentDto> makeAppointment(@PathVariable UUID sellerUuid, @PathVariable MaterialType materialType,
                                                  @PathVariable String licensePlateNumberOfTruck, @PathVariable LocalDateTime appointmentTime) {

            CreatedAppointmentDto createdAppointmentDto = makeAppointmentUseCase.makeAppointment(new MakeAppointmentCommand(new Seller.CustomerUUID(sellerUuid), materialType,
                    licensePlateNumberOfTruck, appointmentTime));

            return ResponseEntity.ok(createdAppointmentDto);
    }

    @PostMapping("appointment/truck/{licensePlateNumber}/check")
    public ResponseEntity<TruckArrivedDto> scanTruckForAppointment(@PathVariable String licensePlateNumber){

        TruckArrivedDto truckArrivedDto = scanLicensePlateNumberOfATruckUseCase.scanLicensePlateNumber(new ScanLicensePlateNumberWhenArrivingCommand(licensePlateNumber));
        return ResponseEntity.ok(truckArrivedDto);
    }

    @PostMapping("appointment/{appointmentUuid}/weight/{weighingTime}")
    public ResponseEntity<String> weightTruckDuringAppointment(@PathVariable UUID appointmentUuid, @PathVariable WeightingTime weighingTime){

        weightTruckUseCase.weightTruck(new WeightTruckCommand(new Appointment.AppointmentUUID(appointmentUuid), weighingTime));
        return ResponseEntity.ok("The truck was successfully weighted for " + weighingTime );
    }

    @PostMapping("appointment/{appointmentUuid}/loadMaterial")
    public ResponseEntity<String> loadMaterial(@PathVariable UUID appointmentUuid){
        deliverMaterialUseCase.deliverMaterial(new DeliverMaterialCommand(new Appointment.AppointmentUUID(appointmentUuid)));
        return ResponseEntity.ok("The truck has successfully delivered the material");
    }
}
