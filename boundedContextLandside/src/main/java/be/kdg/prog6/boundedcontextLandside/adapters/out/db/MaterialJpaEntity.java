package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class MaterialJpaEntity {

    @Id
    @Column(nullable = false)
    private MaterialType materialType;

    @Column(nullable = false)
    private String description;

    public MaterialJpaEntity(MaterialType materialType, String description) {

        this.materialType = materialType;
        this.description = description;
    }

    public MaterialJpaEntity() {

    }
    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
