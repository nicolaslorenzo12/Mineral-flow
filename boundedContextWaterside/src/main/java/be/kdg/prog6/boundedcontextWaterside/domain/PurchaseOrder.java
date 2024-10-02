package be.kdg.prog6.boundedcontextWaterside.domain;

import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.util.List;

public class PurchaseOrder {

    private final String poNumber;
    private final Seller.CustomerUUID sellerUuid;
    private final Buyer.CustomerUUID buyerUuid;
    private final String vesselNumber;
    private final LocalDate date;
    private final List<OrderLine> orderLineList;

    public PurchaseOrder(String poNumber, Customer.CustomerUUID sellerUuid, Customer.CustomerUUID buyerUuid,
                         String vesselNumber, LocalDate date, List<OrderLine> orderLineList) {
        this.poNumber = poNumber;
        this.sellerUuid = sellerUuid;
        this.buyerUuid = buyerUuid;
        this.vesselNumber = vesselNumber;
        this.date = date;
        this.orderLineList = orderLineList;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public Seller.CustomerUUID getSellerUuid() {
        return sellerUuid;
    }

    public Buyer.CustomerUUID getBuyerUuid() {
        return buyerUuid;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }
}
