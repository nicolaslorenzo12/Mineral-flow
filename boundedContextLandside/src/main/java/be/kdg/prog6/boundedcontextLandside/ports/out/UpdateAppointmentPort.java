package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;

public interface UpdateAppointmentPort {

    void updateAppointmentTruckStatus(Appointment.AppointmentUUID appointmentUUIDj, TruckStatus truckStatus);
    void updateAppointmentInitialOrFinalWeight(Appointment.AppointmentUUID appointmentUUID, int weight, int weighingCount);
    void updateAppointmentArrivalOrDepartureTime(Appointment.AppointmentUUID appointmentUUID, TruckStatus truckStatus);
}
