package be.kdg.prog6.boundedcontextLandside.domain;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Pdt;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private Seller.CustomerUUID sellerUUID;
    private int gateNumber;
    private final LocalDateTime appointmentTime;
    private Material.MaterialUUID materialUUID;
    private Truck.TruckUUID truckUUID;
    private TruckStatus status;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;
    private AppointmentUUID appointmentUUID;

    public Appointment(LocalDateTime appointmentTime, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.appointmentTime = appointmentTime;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public record AppointmentUUID(UUID uuid) {

    }
}
