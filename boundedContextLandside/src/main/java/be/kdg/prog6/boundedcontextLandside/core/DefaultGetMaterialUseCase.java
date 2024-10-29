package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.ports.in.GetMaterialByMaterialDescriptionCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetMaterialByMaterialTypeCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("landsideGetMaterialUseCase")
public class DefaultGetMaterialUseCase implements GetMaterialUseCase {

    private final LoadMaterialPort loadMaterialPort;

    public DefaultGetMaterialUseCase(LoadMaterialPort loadMaterialPort) {
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public Material getMaterialByMaterialType(GetMaterialByMaterialTypeCommand getMaterialCommand) {
        return loadMaterialPort.loadMaterialByMaterialType(getMaterialCommand.materialType())
                .orElseThrow(() -> new NoSuchElementException("Material was not found"));
    }

    @Override
    public Material getMaterialByMaterialDescription(GetMaterialByMaterialDescriptionCommand getMaterialDescriptionCommand) {
        return loadMaterialPort.loadMaterialByMaterialDescription(getMaterialDescriptionCommand.materialDescription())
                .orElseThrow(() -> new NoSuchElementException("Material was not found"));
    }

    @Override
    public List<Material> getAllMaterials() {
        return loadMaterialPort.loadAllMaterials();
    }
}
