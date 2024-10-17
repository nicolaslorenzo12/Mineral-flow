package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.ports.in.GetMaterialCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("landsideGetMaterialUseCase")
public class DefaultGetMaterialUseCase implements GetMaterialUseCase {

    private final LoadMaterialPort loadMaterialPort;

    public DefaultGetMaterialUseCase(LoadMaterialPort loadMaterialPort) {
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public Material getMaterial(GetMaterialCommand getMaterialCommand) {
        return loadMaterialPort.loadMaterialByMaterialType(getMaterialCommand.materialType())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Material not found"));
    }
}
