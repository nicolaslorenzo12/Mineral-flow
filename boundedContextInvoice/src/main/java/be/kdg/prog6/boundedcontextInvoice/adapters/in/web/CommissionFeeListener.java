package be.kdg.prog6.boundedcontextInvoice.adapters.in.web;

import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateCommissionFeeUseCase;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommissionFeeListener {

    private final CalculateCommissionFeeUseCase calculateCommissionFeeUseCase;

    public CommissionFeeListener(CalculateCommissionFeeUseCase calculateCommissionFeeUseCase) {
        this.calculateCommissionFeeUseCase = calculateCommissionFeeUseCase;
    }

    @RabbitListener(queues = "warehouse.commission_fee")
    public void receiveCommissionFeeToCalculate(final CommissionFeeToCalculateCommand commissionFeeToCalculateCommand) {
        calculateCommissionFeeUseCase.calculateCommissionFee(commissionFeeToCalculateCommand);
    }
}