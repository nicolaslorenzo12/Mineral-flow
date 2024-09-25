package be.kdg.prog6.boundedcontextWarehouse.ports.in;

public interface AddMaterialUseCase {

    void addOrDispatchMaterial(AddMaterialCommand addMaterialCommand);
}
