package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class MaterialJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID materialUUID;

    @Column(nullable = false)
    private MaterialType materialType;

    @Column(nullable = false)
    private String description;

    public MaterialJpaEntity(UUID materialUUID, MaterialType materialType, String description) {
        this.materialUUID = materialUUID;
        this.materialType = materialType;
        this.description = description;
    }

    public MaterialJpaEntity() {

    }

    public UUID getMaterialUUID() {
        return materialUUID;
    }

    public void setMaterialUUID(UUID materialUUID) {
        this.materialUUID = materialUUID;
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
