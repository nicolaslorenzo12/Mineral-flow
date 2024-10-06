package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.dto.CreatedAppointmentDto;

public interface MakeAppointmentUseCase {

    CreatedAppointmentDto makeAppointment(MakeAppointmentCommand makeAppointmentCommand);
}
