package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("invoiceMaterialRepository")
public interface MaterialJpaEntityRepository extends JpaRepository<MaterialJpaEntity, MaterialType> {

    Optional<MaterialJpaEntity> findByMaterialType(MaterialType materialType);
}
