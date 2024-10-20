package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("invoiceMaterialDatabaseAdapter")
public class MaterialDBAdapter implements LoadMaterialPort {

    private final MaterialJpaEntityRepository materialJpaEntityRepository;

    public MaterialDBAdapter(MaterialJpaEntityRepository materialJpaEntityRepository) {
        this.materialJpaEntityRepository = materialJpaEntityRepository;
    }

    @Override
    public Optional<Material> loadMaterialByMaterialType(MaterialType materialType) {
        Optional<MaterialJpaEntity> materialJpaEntity = materialJpaEntityRepository.findByMaterialType(materialType);
        return materialJpaEntity.map(this::buildMaterialObject);
    }

    @Override
    public List<Material> findAllMaterials() {

        List<MaterialJpaEntity> materialJpaEntities = materialJpaEntityRepository.findAll();

        return materialJpaEntities.stream()
                .map(this::buildMaterialObject)
                .toList();
    }

    private Material buildMaterialObject(MaterialJpaEntity materialJpaEntity) {
        return new Material(materialJpaEntity.getMaterialType(), materialJpaEntity.getDescription(),materialJpaEntity.getStoragePricePerTonPerDay(),
                materialJpaEntity.getPricePerTon());
    }
}