package be.kdg.prog6.common.domain;

import java.util.UUID;

public class Material {

    private MaterialType materialType;
    private String description;
    private int storagePricePerTonPerDay;
    private int pricePerTon;
    private MaterialUUID materialUUID;
    public record MaterialUUID(UUID uuid){

    }

}
