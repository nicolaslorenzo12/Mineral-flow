package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("landsideMaterialDatabaseAdapter")
public class MaterialDBAdapter implements LoadMaterialPort {

    private final MaterialRepository materialRepository;

    public MaterialDBAdapter(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> loadMaterialByMaterialType(MaterialType materialType) {

        Optional<MaterialJpaEntity>  materialJpaEntity = materialRepository.findByMaterialType(materialType);
        return materialJpaEntity.map(this::buildMaterialObject);
    }

    @Override
    public Optional<Material> loadMaterialByMaterialDescription(String description) {

        Optional<MaterialJpaEntity> materialJpaEntity = materialRepository.findByDescription(description);
        return materialJpaEntity.map(this::buildMaterialObject);
    }

    @Override
    public List<Material> loadAllMaterials() {
        return materialRepository.findAll().stream().map(this::buildMaterialObject).toList();
    }

    private Material buildMaterialObject(MaterialJpaEntity materialJpaEntity){
        return new Material(materialJpaEntity.getMaterialType(), materialJpaEntity.getDescription());
    }
}

