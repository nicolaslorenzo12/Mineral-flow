package be.kdg.prog6.boundedcontextInvoice.domain;

import be.kdg.prog6.common.domain.Address;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.OrderLine;
import be.kdg.prog6.common.domain.PurchaseOrder;

import java.util.List;

public class Accountant extends Customer {


    public Accountant(CustomerUUID customerUUID, String name, Address address) {
        super(customerUUID, name, address);
    }

    public Accountant(CustomerUUID customerUUID, String name) {
        super(customerUUID, name);
    }

    public static void calculateCommissionFee(List<OrderLine> orderLines) {

        int commissionFee = 0;

        for(OrderLine orderLine : orderLines) {

            commissionFee += orderLine.getQuantity() * 2;
        }

        System.out.println("The commission fee is " + commissionFee + "$");
    }
}
