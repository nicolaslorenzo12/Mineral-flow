package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.domain.Accountant;
import be.kdg.prog6.boundedcontextInvoice.domain.QuantityOfTonsInOrderLineAndPriceOfMaterial;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateCommissionFeeUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.facades.CommissionFeeToCalculateCommand;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultCalculateCommissionFeeUseCase implements CalculateCommissionFeeUseCase {


    private final LoadMaterialPort loadMaterialPort;

    public DefaultCalculateCommissionFeeUseCase(LoadMaterialPort loadMaterialPort) {
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public void calculateCommissionFee(CommissionFeeToCalculateCommand commissionFeeToCalculateCommand) {

        List<QuantityOfTonsInOrderLineAndPriceOfMaterial> quantityAndPriceList = commissionFeeToCalculateCommand.orderLines().stream()
                .map(orderLine -> {
                    Material material = loadMaterialPort.loadMaterialByMaterialType(orderLine.getMaterialType())
                            .orElseThrow(() -> new NoSuchElementException("Material not found"));

                    return new QuantityOfTonsInOrderLineAndPriceOfMaterial(orderLine.getQuantity(), material.getPricePerTon());
                }).toList();

        Accountant.calculateCommissionFee(quantityAndPriceList);
    }
}
