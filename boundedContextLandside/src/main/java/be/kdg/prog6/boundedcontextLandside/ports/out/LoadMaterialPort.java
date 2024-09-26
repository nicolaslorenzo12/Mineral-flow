package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;

import java.util.Optional;

public interface LoadMaterialPort {

    Optional<Material> loadMaterialByMaterialType(MaterialType materialType);
}
