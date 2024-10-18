package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.Material;

import java.util.List;

public interface GetMaterialUseCase {

    Material getMaterialByMaterialType(GetMaterialByMaterialTypeCommand getMaterialCommand);
    Material getMaterialByMaterialDescription(GetMaterialByMaterialDescriptionCommand getMaterialDescriptionCommand);
    List<Material> getAllMaterials();
}
