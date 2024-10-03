package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.common.domain.MaterialType;

public record CreateOrderLineDraftCommand(String poNumber, MaterialType materialType, int quantity){
}
