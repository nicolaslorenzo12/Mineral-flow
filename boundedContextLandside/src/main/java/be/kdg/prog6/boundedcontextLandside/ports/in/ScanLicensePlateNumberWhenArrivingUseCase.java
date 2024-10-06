package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckArrivedDto;

public interface ScanLicensePlateNumberWhenArrivingUseCase {

    TruckArrivedDto scanLicensePlateNumber(ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand);
}
