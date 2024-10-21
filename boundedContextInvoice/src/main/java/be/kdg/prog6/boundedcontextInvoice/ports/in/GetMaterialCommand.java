package be.kdg.prog6.boundedcontextInvoice.ports.in;

import be.kdg.prog6.common.domain.MaterialType;

public record GetMaterialCommand(MaterialType materialType){
}
