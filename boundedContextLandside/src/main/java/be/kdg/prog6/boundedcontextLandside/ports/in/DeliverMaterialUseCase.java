package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.adapters.in.web.dto.LoadedMaterialDto;

public interface DeliverMaterialUseCase {

    LoadedMaterialDto deliverMaterial(DeliverMaterialCommand loadMaterialCommand);
}
