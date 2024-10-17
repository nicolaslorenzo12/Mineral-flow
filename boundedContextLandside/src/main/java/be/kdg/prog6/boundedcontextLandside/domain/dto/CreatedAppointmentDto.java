package be.kdg.prog6.boundedcontextLandside.domain.dto;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreatedAppointmentDto {

    private final String sellerName;
    private final LocalDate day;
    private final int gateNumber;
    private final LocalDateTime appointmentTime;
    private final String materialDescription;
    private final String licensePlateNumberOfTruck;
    private final int warehouseNumber;

    public CreatedAppointmentDto(String sellerName, LocalDate day, int gateNumber, LocalDateTime appointmentTime, String materialDescription,
                                 String licensePlateNumberOfTruck, int warehouseNumber) {
        this.sellerName = sellerName;
        this.day = day;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialDescription = materialDescription;
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
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

    public String getLicensePlateNumberOfTruck() {
        return licensePlateNumberOfTruck;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }
}
