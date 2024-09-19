package be.kdg.prog6.common.domain;

import java.util.UUID;

public class Material {

    private MaterialType materialType;
    private String description;
    private MaterialUUID materialUUID;

    public record MaterialUUID(UUID uuid){

    }

    public Material(MaterialType materialType, String description, MaterialUUID materialUUID) {
        this.materialType = materialType;
        this.description = description;
        this.materialUUID = materialUUID;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public String getDescription() {
        return description;
    }

    public MaterialUUID getMaterialUUID() {
        return materialUUID;
    }
}
