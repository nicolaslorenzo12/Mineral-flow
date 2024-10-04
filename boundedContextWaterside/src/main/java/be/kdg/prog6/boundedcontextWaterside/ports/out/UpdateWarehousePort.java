package be.kdg.prog6.boundedcontextWaterside.ports.out;

import be.kdg.prog6.boundedcontextWaterside.domain.OrderLine;
import be.kdg.prog6.boundedcontextWaterside.domain.PurchaseOrder;
import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;

import java.util.List;

public interface UpdateWarehousePort {
    void updateWarehouse(OrderLine line, Seller.CustomerUUID sellerUUID, Buyer.CustomerUUID buyerUUID);
}
