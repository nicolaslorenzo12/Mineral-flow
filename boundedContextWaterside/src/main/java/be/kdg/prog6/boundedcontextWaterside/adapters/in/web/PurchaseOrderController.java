package be.kdg.prog6.boundedcontextWaterside.adapters.in.web;

import be.kdg.prog6.boundedcontextWaterside.ports.in.CreateOrderLineDraftCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreateOrderLineDraftUseCase;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftCommand;
import be.kdg.prog6.boundedcontextWaterside.ports.in.CreatePurchaseOrderDraftUseCase;
import be.kdg.prog6.common.domain.Buyer;
import be.kdg.prog6.common.domain.MaterialType;
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
    private final CreateOrderLineDraftUseCase createOrderLineDraftUseCase;


    public PurchaseOrderController(CreatePurchaseOrderDraftUseCase createPurchaseOrderDraftUseCase, CreateOrderLineDraftUseCase createOrderLineDraftUseCase) {
        this.createPurchaseOrderDraftUseCase = createPurchaseOrderDraftUseCase;
        this.createOrderLineDraftUseCase = createOrderLineDraftUseCase;
    }



    @PostMapping("new/purchase-order-draft/seller/{sellerUUID}/buyer/{buyerUUID}/vesselNumber/{vesselNumber}/date/{purchaseOrderDate}")
    public ResponseEntity<String> makePurchaseOrderDraft(@PathVariable UUID sellerUUID, @PathVariable UUID buyerUUID, @PathVariable String vesselNumber
                                                        ,@PathVariable LocalDate purchaseOrderDate){
        createPurchaseOrderDraftUseCase.createPurchaseOrder(new CreatePurchaseOrderDraftCommand(new Seller.CustomerUUID(sellerUUID),
                new Buyer.CustomerUUID(buyerUUID), vesselNumber, purchaseOrderDate));

        return ResponseEntity.ok("Purchase order draft was successfully created");
    }

    @PostMapping("new/line-order-draft/purchase-order/{poNumber}/material-type/{materialType}/quantity/{quantity}")
    public ResponseEntity<String> makePurchaseOrderDraft(@PathVariable String poNumber, @PathVariable MaterialType materialType, @PathVariable int quantity){

        createOrderLineDraftUseCase.createOrderLineDraftUseCase(new CreateOrderLineDraftCommand(poNumber, materialType, quantity));

        return ResponseEntity.ok("Order line draft was successfully created");
    }
}
