package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Pdt;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdatePdtPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseDBAdapter implements LoadWarehousePort, UpdateWarehousePort, UpdatePdtPort {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseActivityRepository warehouseActivityRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository, WarehouseActivityRepository warehouseActivityRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseActivityRepository = warehouseActivityRepository;
    }


    @Override
    public Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber) {
        Optional<WarehouseJpaEntity> warehouseJpaEntityOptional = warehouseRepository.findByWarehouseNumber(warehouseNumber);

        if (warehouseJpaEntityOptional.isEmpty()) {
            return Optional.empty();
        }

        Warehouse warehouse = buildWarehouseObject(warehouseJpaEntityOptional.get(), warehouseNumber);
        List<WarehouseJpaActivityEntity> warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouseNumber);
        addWarehouseActivitiesToWarehouseObject(warehouseJpaActivityList, warehouse);
        return Optional.of(warehouse);
    }

    private Warehouse buildWarehouseObject(WarehouseJpaEntity warehouseJpaEntity, int warehouseNumber) {
        return new Warehouse(
                warehouseNumber,
                new Seller.CustomerUUID(warehouseJpaEntity.getSellerUUID()),
                warehouseJpaEntity.getMaterialType(),
                new WarehouseActivityWindow(warehouseNumber)
        );
    }

    private void addWarehouseActivitiesToWarehouseObject(List<WarehouseJpaActivityEntity> warehouseJpaActivityList,
                                                              Warehouse warehouse){
        for(WarehouseJpaActivityEntity warehouseJpaActivity :  warehouseJpaActivityList){
            warehouse.recreateWarehouseActivity(warehouseJpaActivity.getAmountOfTons(),
                    warehouseJpaActivity.getWarehouseNumber(),
                    warehouseJpaActivity.getWarehouseAction());
        }
    }
    @Override
    public void warehouseCreateActivity(Warehouse warehouse, WarehouseActivity warehouseActivity, UUID pdtUUID, int amountOfTonsAdded) {
        final int warehouseNumber = warehouse.getWareHouseNumber();
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.
                findByWarehouseNumber(warehouseNumber).orElseThrow();

        PdtJpaEntity pdtJpaEntity = findtPdtJpaEntityByPdtUUID(pdtUUID, warehouseJpaEntity);
        pdtJpaEntity.setAmountOfTonsDelivered(amountOfTonsAdded);

        warehouseJpaEntity.getActivities().
                add(buildJpaActivityEntity(warehouseJpaEntity, warehouseActivity));

        warehouseRepository.save(warehouseJpaEntity);
    }

    private PdtJpaEntity findtPdtJpaEntityByPdtUUID(UUID pdtUUID, WarehouseJpaEntity warehouseJpaEntity){
        return warehouseJpaEntity.getPdtJpaEntityList().stream().filter(pdtJpaEntity1 ->
                pdtJpaEntity1.getPdtUUID().equals(pdtUUID)).findFirst().orElseThrow(()
                -> new CustomException(HttpStatus.NOT_FOUND, "pdt was not found"));
    }
    private WarehouseJpaActivityEntity buildJpaActivityEntity(final WarehouseJpaEntity warehouseJpaEntity,
                                                              final WarehouseActivity warehouseActivity) {

        final WarehouseJpaActivityEntity warehouseJpaActivityEntity = new WarehouseJpaActivityEntity();
        warehouseJpaActivityEntity.setAmountOfTons(warehouseActivity.amountOfTons());
        warehouseJpaActivityEntity.setWarehouseNumber(warehouseActivity.warehouseNumber());
        warehouseJpaActivityEntity.setWarehouseAction(warehouseActivity.action());
        warehouseJpaActivityEntity.setTime(LocalDateTime.now());
        warehouseJpaActivityEntity.setWarehouseJpaEntity(warehouseJpaEntity);
        return warehouseJpaActivityEntity;
    }

    @Override
    public void createPdtPort(Warehouse warehouse, LocalDateTime timeOfDelivery, UUID appointmentUUID) {

        final int warehouseNumber = warehouse.getWareHouseNumber();
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.
                findByWarehouseNumber(warehouseNumber).orElseThrow();

        PdtJpaEntity pdtJpaEntity = new PdtJpaEntity(appointmentUUID, timeOfDelivery, warehouseNumber);
        warehouseJpaEntity.getPdtJpaEntityList().add(pdtJpaEntity);
        warehouseRepository.save(warehouseJpaEntity);
    }
}
