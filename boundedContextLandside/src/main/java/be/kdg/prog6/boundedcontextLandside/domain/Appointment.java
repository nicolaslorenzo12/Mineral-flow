package be.kdg.prog6.boundedcontextLandside.domain;
import be.kdg.prog6.common.domain.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private final Seller.CustomerUUID sellerUUID;
    private final int gateNumber;
    private final LocalDateTime appointmentTime;
    private final MaterialType materialType;
    private final String licensePlateNumberOfTruck;
    private TruckStatus status;
    private int warehouseNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private double initialWeight;
    private double finalWeight;
    private final AppointmentUUID appointmentUUID;
    public record AppointmentUUID(UUID uuid) {

    }

    public Appointment( final AppointmentUUID appointmentUUID,Seller.CustomerUUID sellerUUID, int gateNumber,
                       LocalDateTime appointmentTime, MaterialType materialType, String licensePlateNumberOfTruck, TruckStatus status)
    {
        this.appointmentUUID = appointmentUUID;
        this.sellerUUID = sellerUUID;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialType = materialType;
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
        this.status = status;
    }

    public Seller.CustomerUUID getSellerUUID() {
        return sellerUUID;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
    public String getLicensePlateNumberOfTruck() {
        return licensePlateNumberOfTruck;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(double initialWeight) {
        this.initialWeight = initialWeight;
    }

    public double getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(double finalWeight) {
        this.finalWeight = finalWeight;
    }

    public AppointmentUUID getAppointmentUUID() {
        return appointmentUUID;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "sellerUUID=" + sellerUUID +
                ", gateNumber=" + gateNumber +
                ", appointmentTime=" + appointmentTime +
                ", materialType=" + materialType +
                ", licensePlateNumberOfTruck=" + licensePlateNumberOfTruck +
                ", status=" + status +
                ", appointmentUUID=" + appointmentUUID +
                '}';
    }
}
