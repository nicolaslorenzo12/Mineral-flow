package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialJpaEntityRepository extends JpaRepository<MaterialJpaEntity, String> {

    MaterialJpaEntity findByMaterialType(MaterialType materialType);
}
