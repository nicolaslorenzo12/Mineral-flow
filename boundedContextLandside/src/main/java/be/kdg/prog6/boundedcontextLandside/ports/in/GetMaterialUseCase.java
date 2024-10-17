package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.Material;

public interface GetMaterialUseCase {

    Material getMaterialByMaterialType(GetMaterialByMaterialTypeCommand getMaterialCommand);
    Material getMaterialByMaterialDescription(GetMaterialByMaterialDescriptionCommand getMaterialDescriptionCommand);
}
