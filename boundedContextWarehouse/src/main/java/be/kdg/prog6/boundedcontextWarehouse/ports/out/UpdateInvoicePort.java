package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.common.domain.PurchaseOrder;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;

public interface UpdateInvoicePort {

    void updateInvoice(CommissionFeeToCalculateCommand commissionFeeToCalculateCommand);
}
