package be.kdg.prog6.boundedcontextInvoice.adapters.in.web;

import be.kdg.prog6.boundedcontextInvoice.domain.Invoice;
import be.kdg.prog6.boundedcontextInvoice.domain.dto.InvoiceDto;
import be.kdg.prog6.boundedcontextInvoice.domain.dto.InvoiceLineDto;
import be.kdg.prog6.boundedcontextInvoice.ports.in.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class InvoiceController {


    private final CreateInvoiceUseCase createInvoiceUseCase;
    private final GetSellerUseCase getSellerUseCase;
    private final GetMaterialUseCase getMaterialUseCase;

    public InvoiceController(CreateInvoiceUseCase createInvoiceUseCase, GetSellerUseCase getSellerUseCase, GetMaterialUseCase getMaterialUseCase) {
        this.createInvoiceUseCase = createInvoiceUseCase;
        this.getSellerUseCase = getSellerUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
    }

    @PostMapping("invoice/seller/{sellerUUID}")
    public ResponseEntity<InvoiceDto> createInvoiceAndReturnInvoiceDto(@PathVariable UUID sellerUUID){


        Invoice invoice = createInvoiceUseCase.createInvoice(new CreateInvoiceCommand(sellerUUID));
        Seller seller = getSellerUseCase.getSellerBySellerUUID(new GetSellerCommand(new Seller.CustomerUUID(sellerUUID)));
        List<InvoiceLineDto> invoiceLineDtos = buildInvoiceLineDtos(invoice);

        InvoiceDto invoiceDto = new InvoiceDto(seller.getName(), invoiceLineDtos, invoice.getTotalAmountToPay());

        return ResponseEntity.ok(invoiceDto);
    }

    private List<InvoiceLineDto> buildInvoiceLineDtos(Invoice invoice) {

        return invoice.getInvoiceLines().stream()
                .map(invoiceLine -> {

                    Material material = getMaterialUseCase.getMaterial(new GetMaterialCommand(invoiceLine.getMaterialType()));
                    String materialDescription = material.getDescription();

                    return new InvoiceLineDto(materialDescription, invoiceLine.getAmountToPayForMaterial());
                })
                .toList();
    }
}
