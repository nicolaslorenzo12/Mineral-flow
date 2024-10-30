package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "Landside", name = "appointment", uniqueConstraints = {
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
                                String licensePlateNumberOfTruck, TruckStatus status, int warehouseNumber, LocalDate day, int initialWeight, int finalWeight,
                                LocalDateTime arrivalTime, LocalDateTime departureTime) {
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
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
    public AppointmentJpaEntity() {

    }

    public UUID getAppointmentUUID() {
        return appointmentUUID;
    }
    public UUID getSellerUuid() {
        return sellerUuid;
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

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public String getLicensePlateNumberOfTruck() {
        return licensePlateNumberOfTruck;
    }

    public TruckStatus getTruckStatus() {
        return status;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public int getFinalWeight() {
        return finalWeight;
    }

    public DailyCalendarJpaEntity getDailyCalendarJpaEntity() {
        return dailyCalendarJpaEntity;
    }

    public void setDailyCalendarJpaEntity(DailyCalendarJpaEntity dailyCalendarJpaEntity) {

        this.dailyCalendarJpaEntity = dailyCalendarJpaEntity;
    }

    public void setTruckStatus(TruckStatus status) {
        this.status = status;
    }
}

