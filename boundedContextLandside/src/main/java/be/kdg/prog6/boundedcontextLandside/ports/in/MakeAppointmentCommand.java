package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MakeAppointmentCommand(Seller.CustomerUUID sellerUUID, MaterialType materialType, String licensePlateNumber, LocalDateTime appointmentTime) {
}
