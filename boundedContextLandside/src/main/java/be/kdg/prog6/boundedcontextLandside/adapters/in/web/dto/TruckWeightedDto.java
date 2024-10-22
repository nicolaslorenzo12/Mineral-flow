package be.kdg.prog6.boundedcontextLandside.adapters.in.web.dto;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

public class TruckWeightedDto {

    private final Appointment.AppointmentUUID appointmentUUID;
    private final String licensePlateNumber;
    private final int initialWeight;
    private final int finalWeight;

    public TruckWeightedDto(Appointment.AppointmentUUID appointmentUUID, String licensePlateNumber, int initialWeight, int finalWeight) {
        this.appointmentUUID = appointmentUUID;
        this.licensePlateNumber = licensePlateNumber;
        this.initialWeight = initialWeight;
        this.finalWeight = finalWeight;
    }

    public Appointment.AppointmentUUID getAppointmentUUID() {
        return appointmentUUID;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getInitialWeight() {
        return initialWeight;
    }

    public int getFinalWeight() {
        return finalWeight;
    }
}
