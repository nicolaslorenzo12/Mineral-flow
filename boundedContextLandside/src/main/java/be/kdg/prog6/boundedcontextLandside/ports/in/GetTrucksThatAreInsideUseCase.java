package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

import java.util.AbstractMap;
import java.util.List;

public interface GetTrucksThatAreInsideUseCase {
    List<AbstractMap.SimpleEntry<Appointment, String>> getTrucksThatAreInside();
}
