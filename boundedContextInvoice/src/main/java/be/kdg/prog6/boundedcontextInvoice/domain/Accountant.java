package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.*;

import java.util.List;

public class Accountant{


    public static void calculateCommissionFee(List<OrderLine> orderLines, List<Material> materials) {

        double commissionFee = 0;

        for(OrderLine orderLine : orderLines) {

            int priceOfMaterial = findPriceOfMaterialByMaterialType(orderLine.getMaterialType(), materials);
            commissionFee += (orderLine.getQuantity() * priceOfMaterial);
        }

        commissionFee = commissionFee * 0.01;

        System.out.println("The commission fee is " + commissionFee + "$");
    }

    private static int findPriceOfMaterialByMaterialType(MaterialType materialType, List<Material> materials) {

        boolean materialNotFound = true;
        int indexInMaterialsList = 0;
        int priceOfMaterial = 0;

        while(materialNotFound && indexInMaterialsList < materials.size()) {

            Material material = materials.get(indexInMaterialsList);

            if(material.getMaterialType().equals(materialType)){
                priceOfMaterial = material.getPricePerTon();
                materialNotFound = false;
            }

            indexInMaterialsList++;
        }

        return priceOfMaterial;
    }
}
