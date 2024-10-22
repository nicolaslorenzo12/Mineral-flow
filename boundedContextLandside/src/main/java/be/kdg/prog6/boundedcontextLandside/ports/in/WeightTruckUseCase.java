package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.adapters.in.web.dto.TruckWeightedDto;

public interface WeightTruckUseCase {

    TruckWeightedDto weightTruck(WeightTruckCommand weightTruckCommand);
}
