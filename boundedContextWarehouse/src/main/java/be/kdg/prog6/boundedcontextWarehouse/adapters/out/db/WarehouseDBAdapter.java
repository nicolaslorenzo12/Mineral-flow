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

        if (warehouseJpaEntity.isEmpty()) {
            return Optional.empty();
        }

        return returnWarehouseWithActivitiesAndPdts(warehouseJpaEntity);
    }

    @Override
    public Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType) {

        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findBySellerUUIDAndMaterialType(sellerUUID.uuid(), materialType);

        if (warehouseJpaEntity.isEmpty()) {
            return Optional.empty();
        }

        return returnWarehouseWithActivitiesAndPdts(warehouseJpaEntity);
    }


    private Optional<Warehouse> returnWarehouseWithActivitiesAndPdts(Optional<WarehouseJpaEntity> warehouseJpaEntity){

        Warehouse warehouse = buildWarehouseObject(warehouseJpaEntity.get());
        addWarehouseActivitiesToWarehouseObject(warehouseJpaEntity.get().getActivities(), warehouse);
        addWarehousePdtsToWarehouseObject(warehouseJpaEntity.get().getPdtJpaEntityList(), warehouse);
        return Optional.of(warehouse);
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
                    warehouseJpaActivity.getWarehouseNumber(),
                    warehouseJpaActivity.getWarehouseAction());
        }
    }

    private void addWarehousePdtsToWarehouseObject(List<PdtJpaEntity> pdtJpaEntityList, Warehouse warehouse){

        for(PdtJpaEntity pdtJpaEntity : pdtJpaEntityList){
            warehouse.addPdt(new Pdt(pdtJpaEntity.getTimeOfDelivery(), pdtJpaEntity.getAmountOfTonsDelivered(),
                    new Pdt.PdtUUID(pdtJpaEntity.getPdtUUID())));
        }
    }
    @Override
    @Transactional
    public void updateWarehouse(UpdateWarehouseAction updateWarehouseAction, Warehouse warehouse, WarehouseActivity warehouseActivity, UUID appointmentUUID) {


        WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouse.getWareHouseNumber())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found"));

        List<PdtJpaEntity> pdtJpaEntities = buildJpaEntityObjects(warehouse);
        warehouseJpaEntity.setPdtJpaEntityList(pdtJpaEntities);
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


    private List<PdtJpaEntity> buildJpaEntityObjects(Warehouse warehouse) {
        return warehouse.getPdtList().stream()
                .map(pdt -> buildPdtJpaEntity(pdt, warehouse))
                .collect(Collectors.toList());
    }

    private PdtJpaEntity buildPdtJpaEntity(Pdt pdt, Warehouse warehouse) {
        return new PdtJpaEntity(
                pdt.getPdtUUID().uuid(),
                pdt.getTimeOfDelivery(),
                warehouse.getWareHouseNumber(),
                pdt.getAmountOfTonsDelivered()
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
