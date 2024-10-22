package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

public interface MakeAppointmentUseCase {

    Appointment makeAppointment(MakeAppointmentCommand makeAppointmentCommand);
}
