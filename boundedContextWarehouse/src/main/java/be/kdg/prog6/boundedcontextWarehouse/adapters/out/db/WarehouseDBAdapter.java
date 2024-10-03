package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Pdt;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdatePdtPort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.domain.WarehouseAction;
import be.kdg.prog6.common.exception.CustomException;
import jakarta.transaction.Transactional;
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
        WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehosue was not found"));

        Warehouse warehouse = buildWarehouseObject(warehouseJpaEntity);
        List<WarehouseJpaActivityEntity> warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouseNumber);
        addWarehouseActivitiesToWarehouseObject(warehouseJpaActivityList, warehouse);
        return Optional.of(warehouse);
    }

    @Override
    public Optional<Warehouse> loadWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType) {

        WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findBySellerUUIDAndMaterialType(sellerUUID.uuid(), materialType)
                .orElseThrow();
        Warehouse warehouse = buildWarehouseObject(warehouseJpaEntity);
        List<WarehouseJpaActivityEntity> warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouse.getWareHouseNumber());
        addWarehouseActivitiesToWarehouseObject(warehouseJpaActivityList, warehouse);
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
    @Override
    @Transactional
    public void warehouseCreateActivity(Warehouse warehouse, WarehouseActivity warehouseActivity, UUID pdtUUID) {
        final int warehouseNumber = warehouse.getWareHouseNumber();
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.
                findByWarehouseNumber(warehouseNumber).orElseThrow();

        if(warehouseActivity.action().equals(WarehouseAction.RECEIVE)) {
            PdtJpaEntity pdtJpaEntity = findtPdtJpaEntityByPdtUUID(pdtUUID, warehouseJpaEntity);
            pdtJpaEntity.setAmountOfTonsDelivered(warehouseActivity.amountOfTons());
        }

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
