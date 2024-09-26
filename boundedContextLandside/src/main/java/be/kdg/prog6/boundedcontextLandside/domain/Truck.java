package be.kdg.prog6.boundedcontextLandside.domain;

import java.util.UUID;

public class Truck {

    private final String licenseNumber;

    public Truck(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
