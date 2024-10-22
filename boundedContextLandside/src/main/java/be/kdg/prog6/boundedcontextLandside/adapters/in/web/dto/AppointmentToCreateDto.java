package be.kdg.prog6.boundedcontextLandside.adapters.in.web.dto;

import java.time.LocalDateTime;

public class AppointmentToCreateDto {

    private final String sellerName;
    private final String materialDescription;
    private final String licensePlateNumber;
    private final LocalDateTime appointmentTime;

    public AppointmentToCreateDto(String sellerName, String materialDescription, String licensePlateNumber, LocalDateTime appointmentTime) {
        this.sellerName = sellerName;
        this.materialDescription = materialDescription;
        this.licensePlateNumber = licensePlateNumber;
        this.appointmentTime = appointmentTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
