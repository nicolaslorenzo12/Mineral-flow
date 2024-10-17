package be.kdg.prog6.boundedcontextLandside.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreatedAppointmentDto {

    private final String sellerName;
    private final LocalDate day;
    private final int gateNumber;
    private final LocalDateTime appointmentTime;
    private final String materialDescription;
    private final String licensePlateNumber;
    private final int warehouseNumber;

    public CreatedAppointmentDto(String sellerName, LocalDate day, int gateNumber, LocalDateTime appointmentTime, String materialDescription,
                                 String licensePlateNumberOfTruck, int warehouseNumber) {
        this.sellerName = sellerName;
        this.day = day;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialDescription = materialDescription;
        this.licensePlateNumber = licensePlateNumberOfTruck;
        this.warehouseNumber = warehouseNumber;
    }

    public String getSellerName() {
        return sellerName;
    }

    public LocalDate getDay() {
        return day;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }
}
