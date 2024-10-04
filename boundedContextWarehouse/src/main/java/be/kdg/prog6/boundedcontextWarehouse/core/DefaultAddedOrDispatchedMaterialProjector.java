package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.Pdt;
import be.kdg.prog6.boundedcontextWarehouse.domain.UpdateWarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedOrDispatchedMaterialProjector;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.common.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultAddedOrDispatchedMaterialProjector implements AddedOrDispatchedMaterialProjector {

    private final LoadWarehousePort loadWarehousePort;
    private final List<UpdateWarehousePort> updateWarehousePort;

    public DefaultAddedOrDispatchedMaterialProjector(LoadWarehousePort loadWarehousePort, final List<UpdateWarehousePort> updateWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    @Transactional
    public void addMaterial(int intitalWeight, int finalWeight, int warehouseNumber, WarehouseAction warehouseAction, UUID pdtUUID) {

        Warehouse warehouse = findWarehouseByWarehouseNumber(warehouseNumber);
        int amountOfTonsDelivered = warehouse.calculateNetWeight(intitalWeight, finalWeight);
        WarehouseActivity warehouseActivity = buildWarehouseActivityAndAddActivityToWarehouse(warehouse, amountOfTonsDelivered, warehouseAction);
        modifypdt(warehouse, pdtUUID, amountOfTonsDelivered);

        updateWarehousePort.forEach(port -> port.updateWarehouse(UpdateWarehouseAction.CREATE_ACTIVIY, warehouse, warehouseActivity, pdtUUID));
    }

    private void modifypdt(Warehouse warehouse, UUID pdtUUID, int amountOfTondsDelivered){
            Optional<Pdt> pdt = Optional.of(warehouse.getPdtList().stream().filter(pdt1 -> pdt1.getPdtUUID().uuid().equals(pdtUUID)).findFirst().orElseThrow());
            pdt.get().setAmountOfTonsDelivered(amountOfTondsDelivered);
    }

    @Override
    @Transactional
    public void dispatchMaterial(Seller.CustomerUUID sellerUUID, MaterialType materialType, WarehouseAction action, int tonsToDispatch) {

        Warehouse warehouse =loadWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(sellerUUID, materialType)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse was not found"));

        WarehouseActivity warehouseActivity = buildWarehouseActivityAndAddActivityToWarehouse(warehouse, tonsToDispatch, WarehouseAction.DISPATCH);
        updateWarehousePort.forEach(port -> port.updateWarehouse(UpdateWarehouseAction.CREATE_ACTIVIY,warehouse, warehouseActivity,UUID.randomUUID()));
    }

    private Warehouse findWarehouseByWarehouseNumber(int warehouseNumber) {
        return loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));
    }

    private WarehouseActivity buildWarehouseActivityAndAddActivityToWarehouse(Warehouse warehouse, int amountOfTons, WarehouseAction action){

        return warehouse.addWarehouseActivity(amountOfTons,
                warehouse.getWareHouseNumber(),
                action);
    }
}
