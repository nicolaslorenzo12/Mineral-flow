package be.kdg.prog6.boundedcontextLandside.domain.dto;

public class TruckWeightedDto {

    private final String licensePlateNumber;
    private final int initialWeight;
    private final int finalWeight;

    public TruckWeightedDto(String licensePlateNumber, int initialWeight, int finalWeight) {
        this.licensePlateNumber = licensePlateNumber;
        this.initialWeight = initialWeight;
        this.finalWeight = finalWeight;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public int getFinalWeight() {
        return finalWeight;
    }
}
