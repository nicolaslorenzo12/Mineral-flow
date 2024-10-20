package be.kdg.prog6.common.facades;

import be.kdg.prog6.common.domain.OrderLine;

import java.util.List;
import java.util.UUID;

public record CommissionFeeToCalculateCommand(UUID sellerUUID, List<OrderLine> orderLines) {
}
