package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadWarehousePort;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

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

        Optional<WarehouseJpaEntity> warehouseJpaEntity = warehouseRepository.findByWareHouseNumber(warehouseNumber);

        if(warehouseJpaEntity.isEmpty()){
            return Optional.empty();
        }

        Warehouse warehouse = new Warehouse(warehouseNumber, new Seller.CustomerUUID(warehouseJpaEntity.get().getSellerUUID()),
                new Material.MaterialUUID(warehouseJpaEntity.get().getMaterialUUID()),
                new WarehouseActivityWindow());

        List<WarehouseJpaActivityEntity> warehouseJpaActivityList = null;

        warehouseJpaActivityList = warehouseActivityRepository.findByWarehouseNumber(warehouseNumber);

        for(WarehouseJpaActivityEntity warehouseJpaActivity :  warehouseJpaActivityList){
            warehouse.addWarehouseActivity(warehouseJpaActivity.getAmountOfTons(),warehouseJpaActivity.getWarehouseNumber() , new Material.MaterialUUID(warehouseJpaActivity.getMaterialUUID()));
        }

        return Optional.of(warehouse);
    }

    @Override
    public void warehouseActivityCreated(Warehouse warehouse, WarehouseActivity warehouseActivity) {
        final int warehouseNumber = warehouse.getWareHouseNumber();
        final WarehouseJpaEntity warehouseJpaEntity = warehouseRepository.findByWareHouseNumber(warehouseNumber).orElseThrow();
        warehouseJpaEntity.getActivities().add();
    }

//    private WarehouseJpaActivityEntity toPiggyBankActivity(final PiggyBankJpaEntity piggyBankJpaEntity,
//                                                           final Activity activity) {
//        final PiggyBankActivityJpaEntity piggyBankActivityJpaEntity = new PiggyBankActivityJpaEntity();
//        piggyBankActivityJpaEntity.setId(PiggyBankActivityJpaId.of(activity.id()));
//        piggyBankActivityJpaEntity.setType(activity.type());
//        piggyBankActivityJpaEntity.setAmount(activity.amount());
//        piggyBankActivityJpaEntity.setTime(activity.time());
//        piggyBankActivityJpaEntity.setPiggyBank(piggyBankJpaEntity);
//        return piggyBankActivityJpaEntity;
//    }

}
