package be.kdg.prog6.boundedcontextWaterside.adapters.out.db;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog = "Waterside")
public class ShipmentOrderJpaEntity {

    @Id
    private UUID shipmentOrderUUID;
    @Column(nullable = false)
    private LocalDate estimatedArrivalDate;
    @Column(nullable = false)
    private LocalDate estimatedDepartureDate;
    private LocalDate actualArrivalDate;
    private LocalDate actualDepartureDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
    @Column(nullable = false)
    private String poNumber;


    public ShipmentOrderJpaEntity(UUID shipmentOrderUUID, LocalDate estimatedArrivalDate, LocalDate estimatedDepartureDate, LocalDate actualArrivalDate,
                                  LocalDate actualDepartureDate, ShipmentStatus shipmentStatus, String poNumber) {
        this.shipmentOrderUUID = shipmentOrderUUID;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.estimatedDepartureDate = estimatedDepartureDate;
        this.actualArrivalDate = actualArrivalDate;
        this.actualDepartureDate = actualDepartureDate;
        this.shipmentStatus = shipmentStatus;
        this.poNumber = poNumber;
    }

    public ShipmentOrderJpaEntity() {

    }

    public LocalDate getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public void setEstimatedArrivalDate(LocalDate estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }

    public LocalDate getEstimatedDepartureDate() {
        return estimatedDepartureDate;
    }

    public void setEstimatedDepartureDate(LocalDate estimatedDepartureDate) {
        this.estimatedDepartureDate = estimatedDepartureDate;
    }

    public LocalDate getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(LocalDate actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public LocalDate getActualDepartureDate() {
        return actualDepartureDate;
    }

    public void setActualDepartureDate(LocalDate actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public UUID getShipmentOrderUUID() {
        return shipmentOrderUUID;
    }

    public void setShipmentOrderUUID(UUID shipmentOrderUUID) {
        this.shipmentOrderUUID = shipmentOrderUUID;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
}
