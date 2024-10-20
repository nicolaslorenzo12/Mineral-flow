package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.OrderLine;
import be.kdg.prog6.common.domain.Pdt;

import java.util.List;
import java.util.UUID;

public interface UpdateInvoicePort {

    void sendDataForCommissionFeeCalculationInInvoice(UUID sellerUUID, List<OrderLine> orderLines);
    void sendAllPdtForBillingInInvoice(List<Pdt> allPdt);
}
