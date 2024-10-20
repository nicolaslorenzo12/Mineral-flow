package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaterialDBAdapter implements LoadMaterialPort {

    private final MaterialJpaEntityRepository materialJpaEntityRepository;

    public MaterialDBAdapter(MaterialJpaEntityRepository materialJpaEntityRepository) {
        this.materialJpaEntityRepository = materialJpaEntityRepository;
    }

    @Override
    public Material loadMaterialPort(MaterialType materialType) {

        MaterialJpaEntity materialJpaEntity = materialJpaEntityRepository.findByMaterialType(materialType);
        return buildMaterialObject(materialJpaEntity);
    }

    @Override
    public List<Material> loadAllMaterials() {

        List<MaterialJpaEntity> materialJpaEntities = materialJpaEntityRepository.findAll();

        return materialJpaEntities.stream()
                .map(this::buildMaterialObject)
                .collect(Collectors.toList());
    }

    public Material buildMaterialObject(MaterialJpaEntity materialJpaEntity) {

        return new Material(materialJpaEntity.getMaterialType(),
                            materialJpaEntity.getDescription(),
                            materialJpaEntity.getStoragePricePerTonPerDay(),
                            materialJpaEntity.getPricePerTon());
    }
}