package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Accountant{


    public static void calculateCommissionFee( List<QuantityOfTonsInOrderLineAndPriceOfMaterial> quantityAndPriceList) {

        double commissionFee = 0;

        for (QuantityOfTonsInOrderLineAndPriceOfMaterial qp : quantityAndPriceList) {
            commissionFee += qp.getQuantity() * qp.getPricePerTon();
        }

        commissionFee = commissionFee * 0.01;

        System.out.println("The commission fee is " + commissionFee + "$");
    }

//    public static Invoice calculateAncCreateInvoice(List<InvoiceRecord> invoiceRecords, String sellerName, List<Material> materials){
//
//        // Step 1: Group the invoice records by MaterialType
//        Map<MaterialType, List<InvoiceRecord>> invoiceRecordsByMaterialType = invoiceRecords.stream()
//                .collect(Collectors.groupingBy(InvoiceRecord::getMaterialType));
//
//        // Step 2: For each material type, calculate the amountToPayForMaterial and sum the tons
//        List<InvoiceMaterialAndAmountToPayForMaterial> invoiceMaterialAndAmounts = new ArrayList<>();
//        int totalAmountToPayForInvoice = 0;
//
//        for (Map.Entry<MaterialType, List<InvoiceRecord>> entry : invoiceRecordsByMaterialType.entrySet()) {
//            MaterialType materialType = entry.getKey();
//            List<InvoiceRecord> recordsForMaterial = entry.getValue();
//            Material material = findMaterialByMaterialType(materialType, materials);
//
//            int totalAmountToPayForMaterial = 0;  // Reset per material type
//
//            // Calculate the amount to pay for each record based on the number of days in storage
//            for (InvoiceRecord invoiceRecord : recordsForMaterial) {
//                int amountOfTons = invoiceRecord.getAmountOfTons();
//
//                // Calculate the number of days the material has been in storage
//                LocalDate pdtCreationDate = invoiceRecord.getPdtCreationDate();
//                LocalDate invoiceDate = invoiceRecord.getInvoiceDate();  // Use the invoice date for the calculation
//                int daysInStorage = (int) ChronoUnit.DAYS.between(pdtCreationDate, invoiceDate);
//
//                // Get material info and calculate the price
//                int pricePerTonPerDay = material.getStoragePricePerTonPerDay();
//
//                // Calculate the amount to pay for this record (tons * price per ton * days in storage)
//                int amountToPayForRecord = amountOfTons * pricePerTonPerDay * daysInStorage;
//
//                // Add to the total for this material type
//                totalAmountToPayForMaterial += amountToPayForRecord;
//            }
//
//            // Step 3: Calculate the total amount to pay for this material type and add to the list
//            String materialDescription = material.getDescription();  // Or any way to describe the material
//            invoiceMaterialAndAmounts.add(new InvoiceMaterialAndAmountToPayForMaterial(materialDescription, totalAmountToPayForMaterial));
//
//            // Add the total amount for this material type to the overall invoice total
//            totalAmountToPayForInvoice += totalAmountToPayForMaterial;
//        }
//
//        // Step 4: Create and return the Invoice object with the details
//        return new Invoice(sellerName,  invoiceMaterialAndAmounts, totalAmountToPayForInvoice);
//
//    }
//
//
//    private static Material findMaterialByMaterialType(MaterialType materialType, List<Material> materials) {
//
//
//        return materials.stream()
//                .filter(material -> material.getMaterialType().equals(materialType))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Material with type " + materialType + " was not found"));
//    }










    public static Invoice calculateAndCreateInvoice(List<InvoiceRecord> invoiceRecords, Seller.CustomerUUID sellerUUID, List<Material> materials) {

        // First step.  I group the invoice records by MaterialType
        Map<MaterialType, List<InvoiceRecord>> invoiceRecordsByMaterialType = groupInvoiceRecordsByMaterialType(invoiceRecords);

        // Second step. I calculate the amount to pay for each material
        List<InvoiceLine> invoiceMaterialAndAmounts = new ArrayList<>();
        int totalAmountToPayForInvoice = 0;

        for (Map.Entry<MaterialType, List<InvoiceRecord>> entry : invoiceRecordsByMaterialType.entrySet()) {
            MaterialType materialType = entry.getKey();
            List<InvoiceRecord> recordsForMaterial = entry.getValue();
            int priceOfStorageOfMaterial = findPriceOfStorageOfMaterialByMaterialType(materialType, materials);

            int totalAmountToPayForMaterial = calculateAmountToPayForMaterial(recordsForMaterial, priceOfStorageOfMaterial);

            //Third step. I add the material type and amount to pay for that material to the list

            invoiceMaterialAndAmounts.add(new InvoiceLine(materialType, totalAmountToPayForMaterial));

            // Third step part two. I add the total amount of the material to the total amount to pay for the invoice
            totalAmountToPayForInvoice += totalAmountToPayForMaterial;
            System.out.println(totalAmountToPayForMaterial + "sdfadsfsadfadsfadsf");
        }

        //Fourth and last step, I create and return the Invoice object
        return new Invoice(sellerUUID, invoiceMaterialAndAmounts, totalAmountToPayForInvoice);
    }

    private static Map<MaterialType, List<InvoiceRecord>> groupInvoiceRecordsByMaterialType(List<InvoiceRecord> invoiceRecords) {
        return invoiceRecords.stream()
                .collect(Collectors.groupingBy(InvoiceRecord::getMaterialType));
    }


    private static int calculateAmountToPayForMaterial(List<InvoiceRecord> recordsForMaterial, int priceOfMaterial) {

        int totalAmountToPayForMaterial = 0;

        for (InvoiceRecord invoiceRecord : recordsForMaterial) {
            totalAmountToPayForMaterial += calculateAmountToPayForRecord(invoiceRecord, priceOfMaterial);
        }

        return totalAmountToPayForMaterial;
    }


    private static int calculateAmountToPayForRecord(InvoiceRecord invoiceRecord, int priceOfMaterial) {
        int amountOfTons = invoiceRecord.getAmountOfTons();

        // I calculate how many days a record has been in storage
        LocalDate pdtCreationDate = invoiceRecord.getPdtCreationDate();
        LocalDate invoiceDate = invoiceRecord.getInvoiceDate(); // Use the invoice date for the calculation
        int daysInStorage = (int) ChronoUnit.DAYS.between(pdtCreationDate, invoiceDate);

        if(daysInStorage == 0){
            daysInStorage = 1;
        }

        return amountOfTons * priceOfMaterial * daysInStorage;
    }


    private static int findPriceOfStorageOfMaterialByMaterialType(MaterialType materialType, List<Material> materials) {
        Material materialToLookFor = materials.stream()
                .filter(material -> material.getMaterialType().equals(materialType))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Material with type " + materialType + " was not found"));

        return materialToLookFor.getStoragePricePerTonPerDay();
    }
}
