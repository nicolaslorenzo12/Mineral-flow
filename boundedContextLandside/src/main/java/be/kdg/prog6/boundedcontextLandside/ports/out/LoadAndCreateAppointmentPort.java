package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadAndCreateAppointmentPort {

    Appointment loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck);
    void createAppointment(Appointment appointment);
}
