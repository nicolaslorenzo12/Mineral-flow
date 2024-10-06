package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.dto.LoadedMaterialDto;

public interface DeliverMaterialUseCase {

    LoadedMaterialDto deliverMaterial(DeliverMaterialCommand loadMaterialCommand);
}
