package be.kdg.prog6.boundedcontextInvoice.ports.out;

import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;

public interface CalculateCommissionFeeUseCase{

    void calculateCommissionFee(CommissionFeeToCalculateCommand commissionFeeToCalculateCommand);
}
