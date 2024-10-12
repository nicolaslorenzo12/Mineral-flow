package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.Accountant;
import be.kdg.prog6.boundedcontextInvoice.ports.out.CalculateCommissionFeeUseCase;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;
import org.springframework.stereotype.Service;

@Service
public class DefaultCalculateCommissionFeeUseCase implements CalculateCommissionFeeUseCase {


    @Override
    public void calculateCommissionFee(CommissionFeeToCalculateCommand commissionFeeToCalculateCommand) {

        Accountant.calculateCommissionFee(commissionFeeToCalculateCommand.orderLines(), commissionFeeToCalculateCommand.materials());
    }
}
