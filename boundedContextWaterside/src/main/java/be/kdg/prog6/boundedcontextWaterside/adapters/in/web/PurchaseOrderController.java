package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftUseCase;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class PurchaseOrderController {

    private final CreatePurchaseOrderDraftUseCase createPurchaseOrderDraftUseCase;


    public PurchaseOrderController(CreatePurchaseOrderDraftUseCase createPurchaseOrderDraftUseCase) {
        this.createPurchaseOrderDraftUseCase = createPurchaseOrderDraftUseCase;
    }



    @PostMapping("new/purchase-order-draft/seller/{sellerUUID}/buyer/{buyerUUID}/vesselNumber/{vesselNumber}/date/{purchaseOrderDate}")
    public ResponseEntity<String> makePurchaseOrderDraft(@PathVariable UUID sellerUUID, @PathVariable UUID buyerUUID, @PathVariable String vesselNumber
                                                        ,@PathVariable LocalDate purchaseOrderDate){
        createPurchaseOrderDraftUseCase.createPurchaseOrder(new CreatePurchaseOrderDraftCommand(new Seller.CustomerUUID(sellerUUID),
                new Buyer.CustomerUUID(buyerUUID), vesselNumber, purchaseOrderDate));

        return ResponseEntity.ok("Purchase order draft was created");
    }
}
