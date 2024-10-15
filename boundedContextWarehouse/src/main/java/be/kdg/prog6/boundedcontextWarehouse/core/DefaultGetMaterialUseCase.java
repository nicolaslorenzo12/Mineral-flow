package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetMaterialCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetMaterialUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import org.springframework.stereotype.Service;

@Service
public class DefaultGetMaterialUseCase implements GetMaterialUseCase {

    private final LoadMaterialPort loadMaterialPort;

    public DefaultGetMaterialUseCase(LoadMaterialPort loadMaterialPort) {
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public Material getMaterial(GetMaterialCommand getMaterialCommand) {
        return loadMaterialPort.loadMaterialPort(getMaterialCommand.materialType());
    }
}
