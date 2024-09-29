package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
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
    private final ScanLicensePlateNumberOfATruckUseCase scanLicensePlateNumberOfATruckUseCase;
    private final WeightTruckUseCase weightTruckUseCase;
    public AppointmentController(MakeAppointmentUseCase makeAppointmentUseCase, ScanLicensePlateNumberOfATruckUseCase scanLicensePlateNumberOfATruckUseCase, WeightTruckUseCase weightTruckUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
        this.scanLicensePlateNumberOfATruckUseCase = scanLicensePlateNumberOfATruckUseCase;
        this.weightTruckUseCase = weightTruckUseCase;
    }


    @PostMapping("material-truck-appointment/seller/{sellerUuid}/material/{materialType}/licensePlateNumbe/{licensePlateNumberOfTruck}/appointment/{appointmentTime}")
    public ResponseEntity<String> makeAppointment(@PathVariable UUID sellerUuid, @PathVariable MaterialType materialType,
                                                  @PathVariable String licensePlateNumberOfTruck, @PathVariable LocalDateTime appointmentTime) {

            makeAppointmentUseCase.makeAppointment(new MakeAppointmentCommand(new Seller.CustomerUUID(sellerUuid), materialType,
                    licensePlateNumberOfTruck, appointmentTime));

            return ResponseEntity.ok("The appointment was created successfully.");
    }

    @PostMapping("appointment/truck/{licensePlateNumber}/check")
    public ResponseEntity<String> scanTruckForAppointment(@PathVariable String licensePlateNumber){

        scanLicensePlateNumberOfATruckUseCase.scanLicensePlateNumber(new ScanLicensePlateNumberOfATruckCommand(licensePlateNumber));
        return ResponseEntity.ok("The truck is arriving at an acceptable time");
    }

    @PostMapping("appointment/{appointmentUuid}/weight/{weighingCount}")
    public ResponseEntity<String> weightTruckDuringAppointment(@PathVariable UUID appointmentUuid, @PathVariable int weighingCount){

        weightTruckUseCase.weightTruck(new WeightTruckCommand(new Appointment.AppointmentUUID(appointmentUuid), weighingCount));
        return ResponseEntity.ok("The truck was successfully weighted, it has been weighted " + weighingCount + " time");
    }
}
