package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.OrderLine;
import be.kdg.prog6.common.domain.PurchaseOrder;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;

import java.util.List;
import java.util.UUID;

public interface UpdateInvoicePort {

    void updateInvoice(UUID sellerUUID, UUID buyerUUID, List<OrderLine> orderLines, List<Material> materials);
}
