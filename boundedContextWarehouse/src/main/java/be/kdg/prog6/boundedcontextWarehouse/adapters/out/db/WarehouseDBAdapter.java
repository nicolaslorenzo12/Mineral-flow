package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class WarehouseDBAdapter implements LoadWarehousePort, UpdateWarehousePort {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseActivityRepository warehouseActivityRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository, WarehouseActivityRepository warehouseActivityRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseActivityRepository = warehouseActivityRepository;
    }


    @Override
    public Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber) {

        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouseNumber);

        if(warehouseJpaEntity.isEmpty()){
            return Optional.empty();
        }

        Warehouse warehouse = new Warehouse(warehouseNumber, new Seller.CustomerUUID(warehouseJpaEntity.get().getSellerUUID()),
                new Material.MaterialUUID(warehouseJpaEntity.get().getMaterialUUID()),
                new WarehouseActivityWindow());

        List<WarehouseJpaActivityEntity> warehouseJpaActivityList = null;

        warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouseNumber);

        for(WarehouseJpaActivityEntity warehouseJpaActivity :  warehouseJpaActivityList){
            warehouse.addWarehouseActivity(warehouseJpaActivity.getAmountOfTons(), new Seller.CustomerUUID(warehouseJpaActivity.getSellerUUID()),
                    new Material.MaterialUUID(warehouseJpaActivity.getMaterialUUID()),warehouseJpaActivity.getWarehouseNumber());
        }

        return Optional.of(warehouse);
    }

    @Override
    public void warehouseActivityCreated(Warehouse warehouse, WarehouseActivity warehouseActivity) {
        final int warehouseNumber = warehouse.getWareHouseNumber();
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findByWarehouseNumber(warehouseNumber).orElseThrow();
        warehouseJpaEntity.getActivities().add(toWarehouseActivity(warehouseJpaEntity, warehouseActivity));
        warehouseRepository.save(warehouseJpaEntity);
    }

    private WarehouseJpaActivityEntity toWarehouseActivity(final WarehouseJpaEntity warehouseJpaEntity,
                                                           final WarehouseActivity warehouseActivity) {
        final WarehouseJpaActivityEntity warehouseJpaActivityEntity = new WarehouseJpaActivityEntity();
        warehouseJpaActivityEntity.setSellerUUID(warehouseActivity.sellerId().uuid());
        warehouseJpaActivityEntity.setWarehouseNumber(warehouseActivity.warehouseNumber());
        warehouseJpaActivityEntity.setAmountOfTons(warehouseActivity.amountOfTons());
        warehouseJpaActivityEntity.setWarehouseAction(WarehouseAction.RECEIVE);
        warehouseJpaActivityEntity.setMaterialUUID(warehouseActivity.materialUUID().uuid());
        warehouseJpaActivityEntity.setTime(LocalDateTime.now());
        warehouseJpaActivityEntity.setWarehouseJpaEntity(warehouseJpaEntity);
        return warehouseJpaActivityEntity;
    }

}
