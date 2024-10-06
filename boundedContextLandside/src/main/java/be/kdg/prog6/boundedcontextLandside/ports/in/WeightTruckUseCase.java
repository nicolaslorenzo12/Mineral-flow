package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckWeightedDto;

public interface WeightTruckUseCase {

    TruckWeightedDto weightTruck(WeightTruckCommand weightTruckCommand);
}
