package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MaterialDBAdapter implements LoadMaterialPort {

    private final MaterialRepository materialRepository;
    public MaterialDBAdapter(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> loadMaterialByMaterialType(MaterialType materialType) {
        Optional<MaterialJpaEntity>  materialJpaEntity = materialRepository.findByMaterialType(materialType);

        if(materialJpaEntity.isEmpty()){
            return Optional.empty();
        }

        Material material = buildMaterialObject(materialJpaEntity);
        return Optional.of(material);
    }

    private Material buildMaterialObject(Optional<MaterialJpaEntity> materialJpaEntity){
        return new Material(materialJpaEntity.get().getMaterialType(), materialJpaEntity.get().getDescription());
    }
}

