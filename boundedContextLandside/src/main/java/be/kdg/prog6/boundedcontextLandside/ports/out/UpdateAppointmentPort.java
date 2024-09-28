package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

public interface UpdateAppointmentPort {

    void updateAppointmentTruckStatus(Appointment appointment, TruckStatus truckStatus);
}
