package be.kdg.prog6.boundedcontextLandside.domain;
import be.kdg.prog6.common.domain.*;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private final Seller.CustomerUUID sellerUUID;
    private final LocalDate day;
    private final int gateNumber;
    private final LocalDateTime appointmentTime;
    private final MaterialType materialType;
    private final String licensePlateNumberOfTruck;
    private TruckStatus status;
    private final int warehouseNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int initialWeight;
    private int finalWeight;
    private final AppointmentUUID appointmentUUID;
    public record AppointmentUUID(UUID uuid) {

    }

    public Appointment(final AppointmentUUID appointmentUUID, Seller.CustomerUUID sellerUUID, LocalDate localDate, int gateNumber,
                       LocalDateTime appointmentTime, MaterialType materialType, String licensePlateNumberOfTruck, TruckStatus status, int warehouseNumber,
                        int initialWeight, int finalWeight, LocalDateTime arrivalTime, LocalDateTime departureTime)
    {
        this.appointmentUUID = appointmentUUID;
        this.sellerUUID = sellerUUID;
        this.day = localDate;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialType = materialType;
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
        this.status = status;
        this.warehouseNumber = warehouseNumber;
        this.initialWeight = initialWeight;
        this.finalWeight = finalWeight;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public LocalDate getDay() {
        return day;
    }

    public TruckStatus getStatus() {
        return status;
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

    public TruckStatus getTruckStatus() {
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

    public int getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(int initialWeight) {
        this.initialWeight = initialWeight;
    }

    public int getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(int finalWeight) {
        this.finalWeight = finalWeight;
    }

    public AppointmentUUID getAppointmentUUID() {
        return appointmentUUID;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
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

    public void checkIfTruckHasAlreadyGottenThisStatus(TruckStatus truckStatus){

        int currentStatusCode = getTruckStatus().getCode();

        if (truckStatus.getCode() - currentStatusCode != 1) {
            throw new CustomException(HttpStatus.CONFLICT, "The truck's status transitions must follow the correct order. Please ensure all necessary processes have been completed before moving to the next status.");
      }

        updateAppointmentStatus(truckStatus);
    }

    public void proccessWeighting(WeightingTime weightingTime, int weight){

        if (weightingTime.equals(WeightingTime.FIRST_TIME)) {
            checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGFIRSTTIME);
            this.setInitialWeight(weight);
        }
        else {
            checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGLASTTIME);
            this.setStatus(TruckStatus.LEFT);
            this.setFinalWeight(weight);
            setDepartureTime(LocalDateTime.now());
        }
    }

    public void updateAppointmentStatus(TruckStatus truckStatus) {
       this.setStatus(truckStatus);
    }

    public int getNetWeight(){
        return initialWeight - finalWeight;
    }
}
