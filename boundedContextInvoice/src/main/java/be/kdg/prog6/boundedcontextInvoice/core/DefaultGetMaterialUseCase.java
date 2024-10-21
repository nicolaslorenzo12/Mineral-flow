package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.ports.in.GetMaterialCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.GetMaterialUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadMaterialPort;
import be.kdg.prog6.common.domain.Material;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("defaultGetMaterialUseCaseInvoice")
public class DefaultGetMaterialUseCase implements GetMaterialUseCase {

    private final LoadMaterialPort loadMaterialPort;

    public DefaultGetMaterialUseCase(LoadMaterialPort loadMaterialPort) {
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    public Material getMaterial(GetMaterialCommand getMaterialCommand) {
        return loadMaterialPort.loadMaterialByMaterialType(getMaterialCommand.materialType())
                .orElseThrow(() -> new NoSuchElementException("Material not found"));
    }
}
