package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"licensePlateNumberOfTruck", "appointmentTime"})
})
public class AppointmentJpaEntity {
    @Id
    private UUID appointmentUUID;
    @Column(nullable = false)
    private LocalDate day;
    @Column(nullable = false)
    private UUID sellerUuid;
    @Column(nullable = false)
    private int gateNumber;
    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;
    @Column(nullable = false)
    private String licensePlateNumberOfTruck;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TruckStatus status;
    @Column(nullable = false)
    private int warehouseNumber;
    @Column()
    private LocalDateTime arrivalTime;
    @Column()
    private LocalDateTime departureTime;
    @Column()
    private int initialWeight;
    @Column()
    private int finalWeight;
    @ManyToOne
    @JoinColumn(name = "day", referencedColumnName = "day", insertable = false, updatable = false)
    private DailyCalendarJpaEntity dailyCalendarJpaEntity;


    public AppointmentJpaEntity(UUID appointmentUUID, UUID sellerUuid, int gateNumber, LocalDateTime appointmentTime, MaterialType materialType,
                                String licensePlateNumberOfTruck, TruckStatus status, int warehouseNumber, LocalDate day, int initialWeight, int finalWeight) {
        this.appointmentUUID = appointmentUUID;
        this.sellerUuid = sellerUuid;
        this.gateNumber = gateNumber;
        this.appointmentTime = appointmentTime;
        this.materialType = materialType;
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
        this.status = status;
        this.warehouseNumber = warehouseNumber;
        this.day = day;
        this.initialWeight = initialWeight;
        this.finalWeight = finalWeight;
    }
    public AppointmentJpaEntity() {

    }

    public UUID getAppointmentUUID() {
        return appointmentUUID;
    }

    public void setAppointmentUUID(UUID appointmentUUID) {
        this.appointmentUUID = appointmentUUID;
    }

    public UUID getSellerUuid() {
        return sellerUuid;
    }

    public void setSellerUuid(UUID sellerUuid) {
        this.sellerUuid = sellerUuid;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getLicensePlateNumberOfTruck() {
        return licensePlateNumberOfTruck;
    }

    public void setLicensePlateNumberOfTruck(String licensePlateNumberOfTruck) {
        this.licensePlateNumberOfTruck = licensePlateNumberOfTruck;
    }

    public TruckStatus getTruckStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
        this.status = status;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
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

    public DailyCalendarJpaEntity getDailyCalendarJpaEntity() {
        return dailyCalendarJpaEntity;
    }

    public void setDailyCalendarJpaEntity(DailyCalendarJpaEntity dailyCalendarJpaEntity) {
        this.dailyCalendarJpaEntity = dailyCalendarJpaEntity;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
}

