package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MaterialRepository extends JpaRepository<MaterialJpaEntity, MaterialType> {
    Optional<MaterialJpaEntity> findByMaterialType(MaterialType materialType);
}
