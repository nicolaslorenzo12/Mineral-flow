package be.kdg.prog6.boundedcontextWarehouse.ports.out;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;

import java.util.List;

public interface LoadMaterialPort {

    Material loadMaterialPort(MaterialType materialType);
    List<Material> loadAllMaterials();
}
