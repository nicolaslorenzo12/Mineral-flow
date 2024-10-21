package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.domain.Material;

public interface GetMaterialUseCase {

    Material getMaterial(GetMaterialCommand getMaterialCommand);
}
