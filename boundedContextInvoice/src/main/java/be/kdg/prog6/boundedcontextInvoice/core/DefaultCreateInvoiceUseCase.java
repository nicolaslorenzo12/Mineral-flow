package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.Accountant;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.Invoice;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CreateInvoiceUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadInvoiceRecordPort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadMaterialPort;
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

    public DefaultCreateInvoiceUseCase(LoadInvoiceRecordPort loadInvoiceRecordPort, LoadMaterialPort loadMaterialPort) {
        this.loadInvoiceRecordPort = loadInvoiceRecordPort;
        this.loadMaterialPort = loadMaterialPort;
    }


    @Override
    public Invoice createInvoice(CreateInvoiceCommand createInvoiceCommand) {

        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(createInvoiceCommand.sellerUUID());

        List<InvoiceRecord> invoiceRecords = loadInvoiceRecordPort.loadInvoiceRecordsBySellerUUIDAndDate(sellerUUID, LocalDate.now());
        // I use a set to enforce uniqueness of material type
        Set<MaterialType> uniqueMaterialTypes = createAndReturnASetOfMaterialType(invoiceRecords);
        List<Material> uniqueMaterials = createAndReturnAListOfMaterials(uniqueMaterialTypes);

        return Accountant.calculateAndCreateInvoice(invoiceRecords, sellerUUID, uniqueMaterials);
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
