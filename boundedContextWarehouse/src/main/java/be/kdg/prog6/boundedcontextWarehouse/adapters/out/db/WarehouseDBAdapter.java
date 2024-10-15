package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.*;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseDBAdapter implements LoadWarehousePort, UpdateWarehousePort{

    private final WarehouseRepository warehouseRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber) {
        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouseNumber);

        return warehouseJpaEntity.map(this::returnWarehouseWithActivitiesAndPdts);

    }

    @Override
    public Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType) {

        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findBySellerUUIDAndMaterialType(sellerUUID.uuid(), materialType);

        return warehouseJpaEntity.map(this::returnWarehouseWithActivitiesAndPdts);

    }

    @Override
    public List<Warehouse> loadAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(this::returnWarehouseWithActivitiesAndPdts) // Change to map
                .collect(Collectors.toList());
    }


    private Warehouse returnWarehouseWithActivitiesAndPdts(WarehouseJpaEntity warehouseJpaEntity){


        Warehouse warehouse = buildWarehouseObject(warehouseJpaEntity);
        addWarehouseActivitiesToWarehouseObject(warehouseJpaEntity.getActivities(), warehouse);
        addWarehousePdtsToWarehouseObject(warehouseJpaEntity.getStorageJpaEntityList(), warehouse);
        return warehouse;
    }

    private Warehouse buildWarehouseObject(WarehouseJpaEntity warehouseJpaEntity) {
        return new Warehouse(
                warehouseJpaEntity.getWarehouseNumber(),
                new Seller.CustomerUUID(warehouseJpaEntity.getSellerUUID()),
                warehouseJpaEntity.getMaterialType(),
                new WarehouseActivityWindow(warehouseJpaEntity.getWarehouseNumber())
        );
    }


    private void addWarehouseActivitiesToWarehouseObject(List<WarehouseJpaActivityEntity> warehouseJpaActivityList,
                                                              Warehouse warehouse){
        for(WarehouseJpaActivityEntity warehouseJpaActivity :  warehouseJpaActivityList){
            warehouse.recreateWarehouseActivity(warehouseJpaActivity.getAmountOfTons(),
                    warehouseJpaActivity.getWarehouseAction());
        }
    }

    private void addWarehousePdtsToWarehouseObject(List<StorageJpaEntity> storageJpaEntityList, Warehouse warehouse){

        for(StorageJpaEntity storageJpaEntity : storageJpaEntityList){
            warehouse.addPdt(new Pdt(storageJpaEntity.getWarehouseNumber(), storageJpaEntity.getTimeOfDelivery(), storageJpaEntity.getAmountOfTonsDelivered(),
                    new Pdt.PdtUUID(storageJpaEntity.getPdtUUID()), storageJpaEntity.getAmountOfTonsConsumed(), storageJpaEntity.isAllTonsConsumed()));
        }
    }
    @Override
    @Transactional
    public void updateWarehouse(UpdateWarehouseAction updateWarehouseAction, Warehouse warehouse, WarehouseActivity warehouseActivity, UUID appointmentUUID) {


        WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouse.getWareHouseNumber())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));

        List<StorageJpaEntity> pdtJpaEntities = buildJpaEntityObjects(warehouse);
        //warehouseJpaEntity.getPdtJpaEntityList().clear();
        //warehouseJpaEntity.getPdtJpaEntityList().addAll(pdtJpaEntities);
        warehouseJpaEntity.setStorageJpaEntityList(pdtJpaEntities);
        addActivityJpaEntityToWarehouseJpaEntityObject(warehouseJpaEntity, updateWarehouseAction, warehouseActivity);

        warehouseRepository.save(warehouseJpaEntity);
    }

    public void addActivityJpaEntityToWarehouseJpaEntityObject(WarehouseJpaEntity warehouseJpaEntity, UpdateWarehouseAction updateWarehouseAction,
                                                               WarehouseActivity warehouseActivity){

        if(updateWarehouseAction.equals(UpdateWarehouseAction.CREATE_ACTIVIY)){
            warehouseJpaEntity.getActivities().
                    add(buildJpaActivityEntity(warehouseJpaEntity, warehouseActivity));
        }
    }


    private List<StorageJpaEntity> buildJpaEntityObjects(Warehouse warehouse) {
        return warehouse.getPdtList().stream()
                .map(pdt -> buildPdtJpaEntity(pdt, warehouse))
                .collect(Collectors.toList());
    }

    private StorageJpaEntity buildPdtJpaEntity(Pdt pdt, Warehouse warehouse) {
        return new StorageJpaEntity(
                pdt.getPdtUUID().uuid(),
                pdt.getTimeOfDelivery(),
                warehouse.getWareHouseNumber(),
                pdt.getAmountOfTonsDelivered(),
                pdt.getAmountOfTonsConsumed(),
                pdt.isAllTonsConsumed()
        );
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
}
