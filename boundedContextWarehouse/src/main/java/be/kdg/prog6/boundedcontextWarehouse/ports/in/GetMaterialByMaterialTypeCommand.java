package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.MaterialType;

public record GetMaterialByMaterialTypeCommand(MaterialType materialType){
}
