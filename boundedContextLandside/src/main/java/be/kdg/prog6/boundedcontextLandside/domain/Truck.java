package be.kdg.prog6.boundedcontextLandside.domain;

import java.util.UUID;

public class Truck {

    private String licenseNumber;
    private TruckUUID truckUUID;

    public record TruckUUID(UUID uuid){

    }

    public Truck(String licenseNumber, TruckUUID truckUUID) {
        this.licenseNumber = licenseNumber;
        this.truckUUID = truckUUID;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public TruckUUID getTruckUUID() {
        return truckUUID;
    }
}
