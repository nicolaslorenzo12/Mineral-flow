package be.kdg.prog6.boundedcontextWarehouse.ports.in;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.common.domain.Material;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public record AddMaterialCommand(int amount, Material.MaterialUUID materialUUID, int warehouseNumber) {

}
