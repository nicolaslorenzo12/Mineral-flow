package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.WarehouseCapacityExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class AppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public AppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }


    @PostMapping("material-truck-appointment/seller/{sellerUuid}/material/{materialType}/licensePlateNumbe/{licensePlateNumberOfTruck}/appointment/{appointmentTime}")
    public ResponseEntity<String> makeAppointment(@PathVariable UUID sellerUuid, @PathVariable MaterialType materialType,
                                                  @PathVariable String licensePlateNumberOfTruck, @PathVariable LocalDateTime appointmentTime) {
        try {
            makeAppointmentUseCase.makeAppointment(new MakeAppointmentCommand(new Seller.CustomerUUID(sellerUuid), materialType,
                    licensePlateNumberOfTruck, appointmentTime));

            return ResponseEntity.ok("The appointment was created successfully.");

        } catch (WarehouseCapacityExceededException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // HTTP 409 Conflict

        }
    }
}
