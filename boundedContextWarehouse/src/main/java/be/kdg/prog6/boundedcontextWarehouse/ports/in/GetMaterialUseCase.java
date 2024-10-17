package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.common.domain.Material;

public interface GetMaterialUseCase {

    Material getMaterialByMaterialType(GetMaterialByMaterialTypeCommand getMaterialCommand);
}
