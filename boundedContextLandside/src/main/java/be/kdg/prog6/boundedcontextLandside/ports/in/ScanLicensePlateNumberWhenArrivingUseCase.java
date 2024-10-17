package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;

public interface ScanLicensePlateNumberWhenArrivingUseCase {

    Appointment scanLicensePlateNumber(ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand);
}
