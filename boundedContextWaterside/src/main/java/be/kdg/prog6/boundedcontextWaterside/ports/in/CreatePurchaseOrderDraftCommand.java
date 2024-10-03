package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;

public record CreatePurchaseOrderDraftCommand(Seller.CustomerUUID sellerUUID, Buyer.CustomerUUID buyerCustomerUUID, String vesselNumber, LocalDate purchaseOrderDate) {
}
