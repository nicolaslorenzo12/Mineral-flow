package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.Accountant;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.dto.Invoice;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadInvoiceRecordPort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DefaultCreateInvoiceUseCase implements CreateInvoiceUseCase {

    private final LoadInvoiceRecordPort loadInvoiceRecordPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadSellerPort loadSellerPort;

    public DefaultCreateInvoiceUseCase(LoadInvoiceRecordPort loadInvoiceRecordPort, LoadMaterialPort loadMaterialPort, LoadSellerPort loadSellerPort) {
        this.loadInvoiceRecordPort = loadInvoiceRecordPort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadSellerPort = loadSellerPort;
    }


    @Override
    public Invoice createInvoice(CreateInvoiceCommand createInvoiceCommand) {

        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(createInvoiceCommand.sellerUUID());
        Seller seller = loadSellerPort.loadSellerBySellerUUID(sellerUUID)
                .orElseThrow( () -> new NoSuchElementException("Seller was not found"));

        List<InvoiceRecord> invoiceRecords = loadInvoiceRecordPort.loadInvoiceRecordsBySellerUUIDAndDate(sellerUUID, LocalDate.now());
        // I use a set to enforce uniqueness of material type
        Set<MaterialType> uniqueMaterialTypes = createAndReturnASetOfMaterialType(invoiceRecords);
        List<Material> uniqueMaterials = createAndReturnAListOfMaterials(uniqueMaterialTypes);

        return Accountant.calculateAndCreateInvoice(invoiceRecords, seller.getName(), uniqueMaterials);
    }

    private Material findMaterialByMaterialType(MaterialType materialType) {
        return loadMaterialPort.loadMaterialByMaterialType(materialType)
                .orElseThrow(() -> new NoSuchElementException("Material was not found"));
    }

    private Set<MaterialType> createAndReturnASetOfMaterialType(List<InvoiceRecord> invoiceRecords) {

        return invoiceRecords.stream()
                .map(InvoiceRecord::getMaterialType)
                .collect(Collectors.toSet());
    }

    private List<Material> createAndReturnAListOfMaterials(Set<MaterialType> uniqueMaterialTypes) {

        List<Material> uniqueMaterials = new ArrayList<>();

        uniqueMaterialTypes.forEach(materialType -> {
            Material material = findMaterialByMaterialType(materialType);
            uniqueMaterials.add(material);
        });

        return uniqueMaterials;
    }



}
