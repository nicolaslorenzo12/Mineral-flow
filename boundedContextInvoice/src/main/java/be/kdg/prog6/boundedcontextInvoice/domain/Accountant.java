package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.*;

import java.util.List;

public class Accountant{


    public static void calculateCommissionFee( List<QuantityOfTonsInOrderLineAndPriceOfMaterial> quantityAndPriceList) {

        double commissionFee = 0;

        for (QuantityOfTonsInOrderLineAndPriceOfMaterial qp : quantityAndPriceList) {
            commissionFee += qp.getQuantity() * qp.getPricePerTon();
        }

        commissionFee = commissionFee * 0.01;

        System.out.println("The commission fee is " + commissionFee + "$");
    }
}
