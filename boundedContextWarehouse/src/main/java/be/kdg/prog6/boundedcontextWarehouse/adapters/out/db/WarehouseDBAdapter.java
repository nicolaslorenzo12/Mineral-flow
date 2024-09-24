package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WarehouseDBAdapter implements LoadWarehousePort {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseActivityRepository warehouseActivityRepository;

    public WarehouseDBAdapter(WarehouseRepository warehouseRepository, WarehouseActivityRepository warehouseActivityRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseActivityRepository = warehouseActivityRepository;
    }


    @Override
    public Optional<Warehouse> loadWarehouseByWarehouseNumber(int warehouseNumber) {

        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findByWareHouseNumber(warehouseNumber);

        if(warehouseJpaEntity.isEmpty()){
            return Optional.empty();
        }

        Warehouse warehouse = new Warehouse(warehouseNumber, new Seller.CustomerUUID(warehouseJpaEntity.get().getSellerUUID()),
                new Material.MaterialUUID(warehouseJpaEntity.get().getMaterialUUID()),
                new WarehouseActivityWindow());

        List<WarehouseJpaActivity> warehouseJpaActivityList = null;

        warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouseNumber);

        for(WarehouseJpaActivity warehouseJpaActivity :  warehouseJpaActivityList){
            warehouse.addWarehouseActivity(warehouseJpaActivity.getAmountOfTons(),warehouseJpaActivity.getWarehouseNumber() , new Material.MaterialUUID(warehouseJpaActivity.getMaterialUUID()));
        }

        return Optional.of(warehouse);
    }
}
