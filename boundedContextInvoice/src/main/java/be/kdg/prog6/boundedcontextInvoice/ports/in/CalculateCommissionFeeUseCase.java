package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.commands.CommissionFeeToCalculateCommand;

public interface CalculateCommissionFeeUseCase{

    void calculateCommissionFee(CommissionFeeToCalculateCommand commissionFeeToCalculateCommand);
}
