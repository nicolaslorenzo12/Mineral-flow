package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;

public record GetMaterialCommand(MaterialType materialType){
}
