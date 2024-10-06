package be.kdg.prog6.boundedcontextLandside.domain.dto;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreatedAppointmentDto {

    private final Appointment.AppointmentUUID appointmentUUID;
    private final Seller.CustomerUUID sellerUUID;
    private final LocalDate day;
    private final int gateNumber;
    private final LocalDateTime appointmentTime;
    private final MaterialType materialType;
    private final String licensePlateNumberOfTruck;
    private final int warehouseNumber;

    public CreatedAppointmentDto(Appointment.AppointmentUUID appointmentUUID, Seller.CustomerUUID sellerUUID, LocalDate day, int gateNumber, LocalDateTime appointmentTime, MaterialType materialType,
                                 String licensePlateNumberOfTruck, int warehouseNumber)
    {
        this.appointmentUUID = appointmentUUID;
        this.sellerUUID = sellerUUID;
        this.day = day;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialType = materialType;
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
        this.warehouseNumber = warehouseNumber;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
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

    public MaterialType getMaterialType() {
        return materialType;
    }

    public String getLicensePlateNumberOfTruck() {
        return licensePlateNumberOfTruck;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public Appointment.AppointmentUUID getAppointmentUUID() {
        return appointmentUUID;
    }
}
