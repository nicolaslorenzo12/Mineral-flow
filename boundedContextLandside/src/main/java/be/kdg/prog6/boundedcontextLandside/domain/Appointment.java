package be.kdg.prog6.boundedcontextLandside.domain;
import be.kdg.prog6.common.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Appointment {

    private final Seller.CustomerUUID sellerUUID;
    private LocalDate day;
    private int gateNumber;
    private final LocalDateTime appointmentTime;
    private final MaterialType materialType;
    private String licensePlateNumberOfTruck;
    private TruckStatus status;
    private final int warehouseNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int initialWeight;
    private int finalWeight;
    private AppointmentUUID appointmentUUID;
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

    public Appointment(Seller.CustomerUUID sellerUUID, LocalDateTime appointmentTime, MaterialType materialType, String licensePlateNumber,int warehouseNumber){

        this.sellerUUID = sellerUUID;
        this.appointmentTime = appointmentTime;
        this.materialType = materialType;
        this.licensePlateNumberOfTruck = licensePlateNumber;
        this.warehouseNumber = warehouseNumber;
    }

    public LocalDate getDay() {
        return day;
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

    public void setArrivalTime(LocalDateTime arrivalTime, TruckStatus truckStatus) {
        this.checkIfTruckHasAlreadyGottenThisStatus(truckStatus);
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
            throw new IllegalArgumentException("The truck's status transitions must follow the correct order. Please ensure all necessary processes have been completed before moving to the next status.");
      }

        updateAppointmentStatus(truckStatus);
    }

    public void proccessWeighting(){
        if(this.getInitialWeight() == 0){
            checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGFIRSTTIME);
            this.setInitialWeight(generateRandomWeight());
        }
        else{
            checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGLASTTIME);
            this.setStatus(TruckStatus.LEFT);
            this.setFinalWeight(generateRandomWeight());
            setDepartureTime(LocalDateTime.now());
        }
    }

    private int generateRandomWeight() {
        Random random = new Random();
        if(this.getInitialWeight() == 0) {
            return random.nextInt(21) + 15;
        }
        else{
            return 10;
        }
    }

    public void updateAppointmentStatus(TruckStatus truckStatus) {
       this.setStatus(truckStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return gateNumber == that.gateNumber &&
                warehouseNumber == that.warehouseNumber &&
                initialWeight == that.initialWeight &&
                finalWeight == that.finalWeight &&
                sellerUUID.equals(that.sellerUUID) &&
                appointmentTime.equals(that.appointmentTime) &&
                materialType == that.materialType &&
                Objects.equals(licensePlateNumberOfTruck, that.licensePlateNumberOfTruck) &&
                Objects.equals(status, that.status) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(appointmentUUID, that.appointmentUUID);
    }


    @Override
    public int hashCode() {
        return Objects.hash(sellerUUID, gateNumber, appointmentTime, materialType, licensePlateNumberOfTruck, status,
                warehouseNumber, initialWeight, finalWeight, arrivalTime, departureTime, appointmentUUID);
    }


}
