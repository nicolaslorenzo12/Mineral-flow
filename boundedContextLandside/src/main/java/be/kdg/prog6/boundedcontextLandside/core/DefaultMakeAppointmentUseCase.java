package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.WarehouseCapacityExceededException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DefaultMakeAppointmentUseCase implements MakeAppointmentUseCase {
    private final LoadSellerPort loadSellerPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadOrCreateWarehousePort loadOrCreateWarehousePort;
    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;

    public DefaultMakeAppointmentUseCase(LoadSellerPort loadSellerPort, LoadMaterialPort loadMaterialPort, LoadOrCreateWarehousePort loadOrCreateWarehousePort,
                                         LoadAndCreateAppointmentPort loadAndCreateAppointmentPort) {
        this.loadSellerPort = loadSellerPort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
    }

    @Override
    @Transactional
    public void makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        final UUID sellerUUID = makeAppointmentCommand.sellerUUID().uuid();
        final MaterialType materialType = makeAppointmentCommand.materialType();

        final Seller seller = findSellerByUUID(sellerUUID);
        final Material material = findMaterialByType(materialType);
        final Warehouse warehouse = findWarehouseForSellerAndMaterial(seller, material);

        double currentStockPercentage = warehouse.getCurrentStockPercentage();
        checkIfAWarehouseCapacityExceededExceptionIsFound(currentStockPercentage);

        int gateNumber = generateRandomGateNumber();

        Appointment appointment = buildAppointmentObject(seller, gateNumber, makeAppointmentCommand, material, warehouse);
        loadAndCreateAppointmentPort.createAppointment(appointment);
    }

    private Appointment buildAppointmentObject(Seller seller, int gateNumber, MakeAppointmentCommand makeAppointmentCommand, Material material, Warehouse warehouse){

        return new Appointment(new Appointment.AppointmentUUID(UUID.randomUUID()),
                seller.getCustomerUUID(), gateNumber, makeAppointmentCommand.appointmentTime(),
                material.getMaterialType(), makeAppointmentCommand.licensePlateNumber(), TruckStatus.NOTARRIVED,
                warehouse.getWareHouseNumber());
    }

    private void checkIfAWarehouseCapacityExceededExceptionIsFound(double currentStockPercentage){
        if(currentStockPercentage >= 80.00) {
            throw new WarehouseCapacityExceededException("Warehouse is at or above 80% capacity. Cannot schedule an appointment.");
        }
    }
    private Seller findSellerByUUID(UUID sellerUUID) {
        return loadSellerPort.loadSellerByUUID(sellerUUID)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
    private Material findMaterialByType(MaterialType materialType) {
        return loadMaterialPort.loadMaterialByMaterialType(materialType)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }
    private Warehouse findWarehouseForSellerAndMaterial(Seller seller, Material material) {
        return loadOrCreateWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(
                seller.getCustomerUUID().uuid(),
                material.getMaterialType()
        );

    }
    private int generateRandomGateNumber () {
        return (int) (Math.random() * 10) + 1;
    }
}
