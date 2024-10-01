package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

import java.time.LocalDate;

public interface UpdateAppointmentPort {
    void updateAppointment(Appointment appointment, LocalDate day);
}
