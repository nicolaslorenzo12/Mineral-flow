package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.*;
import be.kdg.prog6.boundedcontextLandside.ports.in.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final GetSellerUseCase getSellerUseCase;

    public AppointmentController(MakeAppointmentUseCase makeAppointmentUseCase, ScanLicensePlateNumberWhenArrivingUseCase scanLicensePlateNumberOfATruckUseCase, WeightTruckUseCase weightTruckUseCase, DeliverMaterialUseCase deliverMaterialUseCase, GetMaterialUseCase getMaterialUseCase, GetSellerUseCase getSellerUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
        this.scanLicensePlateNumberOfATruckUseCase = scanLicensePlateNumberOfATruckUseCase;
        this.weightTruckUseCase = weightTruckUseCase;
        this.deliverMaterialUseCase = deliverMaterialUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
        this.getSellerUseCase = getSellerUseCase;
    }


    @PostMapping("material-truck-appointment")
    public ResponseEntity<CreatedAppointmentDto> makeAppointment(@RequestBody AppointmentToCreateDto appointmentToCreateDto) {

        Seller seller = getSellerUseCase.getSellerByName
                (new GetSellerByNameCommand(appointmentToCreateDto.getSellerName()));

        Material material = getMaterialUseCase.getMaterialByMaterialDescription
                (new GetMaterialByMaterialDescriptionCommand(appointmentToCreateDto.getMaterialDescription()));

            Appointment appointment = createAppointment(seller, material, appointmentToCreateDto.getLicensePlateNumber(),
                    appointmentToCreateDto.getAppointmentTime());


        CreatedAppointmentDto createdAppointmentDto = createCreatedAppointmentDto(appointment, seller, material);

            return ResponseEntity.ok(createdAppointmentDto);
    }

    private CreatedAppointmentDto createCreatedAppointmentDto(Appointment appointment, Seller seller, Material material) {
        return new CreatedAppointmentDto(seller.getName(), appointment.getDay(), appointment.getGateNumber(),
                appointment.getAppointmentTime(), material.getDescription(), appointment.getLicensePlateNumberOfTruck(),
                appointment.getWarehouseNumber());
    }

    private Appointment createAppointment(Seller seller, Material material, String licensePlateNumber, LocalDateTime appointmentTime) {

        return  makeAppointmentUseCase.makeAppointment(new MakeAppointmentCommand(seller, material, licensePlateNumber, appointmentTime));
    }

    @PostMapping("appointment/truck/{licensePlateNumber}/check")
    public ResponseEntity<TruckDto> scanTruckForAppointment(@PathVariable String licensePlateNumber){

        Appointment appointment = scanLicensePlateNumberOfATruckUseCase.scanLicensePlateNumber(new ScanLicensePlateNumberWhenArrivingCommand(licensePlateNumber));
        Material material = getMaterialUseCase.getMaterialByMaterialType(new GetMaterialByMaterialTypeCommand(appointment.getMaterialType()));
        Seller seller = getSellerUseCase.getSellerBySellerUUID(new GetSellerByUUIDCommand(appointment.getSellerUUID()));

        TruckDto truckDto = new TruckDto(appointment.getWarehouseNumber(),seller.getName(), appointment.getLicensePlateNumberOfTruck(),
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
