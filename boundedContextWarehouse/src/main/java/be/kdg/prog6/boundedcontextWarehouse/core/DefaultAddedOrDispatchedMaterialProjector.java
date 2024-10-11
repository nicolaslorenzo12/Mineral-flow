package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.domain.*;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.AddedOrDispatchedMaterialProjector;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.*;
import be.kdg.prog6.common.domain.OrderLine;
import be.kdg.prog6.common.domain.PurchaseOrder;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.common.exception.CustomException;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultAddedOrDispatchedMaterialProjector implements AddedOrDispatchedMaterialProjector {

    private final LoadWarehousePort loadWarehousePort;
    private final List<UpdateWarehousePort> updateWarehousePort;
    private final LoadPurchaseOrderPort loadPurchaseOrderPort;
    private final UpdatePurchaseOrderPort updatePurchaseOrderPort;
    private final UpdateInvoicePort updateInvoicePort;

    public DefaultAddedOrDispatchedMaterialProjector(LoadWarehousePort loadWarehousePort, final List<UpdateWarehousePort> updateWarehousePort, LoadPurchaseOrderPort loadPurchaseOrderPort, UpdatePurchaseOrderPort updatePurchaseOrderPort, UpdateInvoicePort updateInvoicePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehousePort = updateWarehousePort;
        this.loadPurchaseOrderPort = loadPurchaseOrderPort;
        this.updatePurchaseOrderPort = updatePurchaseOrderPort;
        this.updateInvoicePort = updateInvoicePort;
    }

    @Override
    @Transactional
    public void addMaterial(int intitalWeight, int finalWeight, int warehouseNumber, WarehouseAction warehouseAction, UUID pdtUUID) {

        Warehouse warehouse = findWarehouseByWarehouseNumber(warehouseNumber);
        int amountOfTonsDelivered = warehouse.calculateNetWeight(intitalWeight, finalWeight);
        WarehouseActivity warehouseActivity = warehouse.addWarehouseActivity(amountOfTonsDelivered, warehouseAction);
        setAmountOfTonsDeliveredToPdt(warehouse, pdtUUID, amountOfTonsDelivered);

        updateWarehousePort.forEach(port -> port.updateWarehouse(UpdateWarehouseAction.CREATE_ACTIVIY, warehouse, warehouseActivity, pdtUUID));
    }

    private void setAmountOfTonsDeliveredToPdt(Warehouse warehouse, UUID pdtUUID, int amountOfTondsDelivered){
            Optional<Pdt> pdt = Optional.of(warehouse.getPdtList().stream().filter(pdt1 -> pdt1.getPdtUUID().uuid().equals(pdtUUID)).findFirst().orElseThrow());
            pdt.get().setAmountOfTonsDelivered(amountOfTondsDelivered);
    }

    @Override
    @Transactional
    public void dispatchMaterial(UUID shipmentOrderUUID) {

        PurchaseOrder purchaseOrder = loadPurchaseOrderPort.loadPurchaseOrderByShipmentOrderUUID(shipmentOrderUUID);
        Seller.CustomerUUID sellerUUID = purchaseOrder.getSellerUuid();

        for (OrderLine orderLine : purchaseOrder.getOrderLineList()) {
            processOrderLine(sellerUUID, orderLine);
        }

        updatePurchaseOrderPort.materialLoaded(shipmentOrderUUID, LocalDate.now());
        updateInvoicePort.updateInvoice(new CommissionFeeToCalculateCommand(purchaseOrder.getSellerUuid().uuid(), purchaseOrder.getBuyerUuid().uuid(), purchaseOrder.getOrderLineList()));
    }

    private void processOrderLine(Seller.CustomerUUID sellerUUID, OrderLine orderLine) {
        Warehouse warehouse = loadWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(sellerUUID, orderLine.getMaterialType())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse was not found"));

        warehouse.removeTonsFromOldestPdts(orderLine.getQuantity());

        WarehouseActivity warehouseActivity = warehouse.addWarehouseActivity(orderLine.getQuantity(),WarehouseAction.DISPATCH);
        updateWarehousePort.forEach(port -> port.updateWarehouse(UpdateWarehouseAction.CREATE_ACTIVIY, warehouse, warehouseActivity,
                UUID.randomUUID()));
    }


    private Warehouse findWarehouseByWarehouseNumber(int warehouseNumber) {
        return loadWarehousePort.loadWarehouseByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));
    }
}
