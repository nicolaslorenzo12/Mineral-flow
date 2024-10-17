package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

import java.util.List;

public interface GetAppointmentsWhichTrucksAreInsideUseCase {
    List<Appointment> getAppointmentsWhichTrucksAreInside();
}
