package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.domain.Pdt;
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.PdtDto;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseDto;
import be.kdg.prog6.boundedcontextWarehouse.domain.dto.WarehouseStockDto;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WarehouseController {
    private final GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase;
    private final GetWarehousesUseCase getWarehousesUseCase;
    private final GetMaterialUseCase getMaterialUseCase;
    private final GetSellerUseCase getSellerUseCase;
    private final GetWarehouseUseCase getWarehouseUseCase;

    public WarehouseController(GetCurrentStockOfAWarehouseUseCase getCurrentStockOfAWarehouseUseCase, GetWarehousesUseCase getWarehousesUseCase, GetMaterialUseCase getMaterialUseCase, GetSellerUseCase getSellerUseCase, GetWarehouseUseCase getWarehouseUseCase) {
        this.getCurrentStockOfAWarehouseUseCase = getCurrentStockOfAWarehouseUseCase;
        this.getWarehousesUseCase = getWarehousesUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
        this.getSellerUseCase = getSellerUseCase;
        this.getWarehouseUseCase = getWarehouseUseCase;
    }

    @GetMapping("current-stock/warehouse/{warehouseNumber}")
    public ResponseEntity<WarehouseStockDto> getCurrentStock(@PathVariable int warehouseNumber) {

        WarehouseStockDto warehouseStockDto = getCurrentStockOfAWarehouseUseCase.
                getCurrentStockOfAWarehouse(new GetCurrentStockOfAWarehouseCommand(warehouseNumber));

        return ResponseEntity.ok(warehouseStockDto);
    }

    @GetMapping("warehouses")
    public ResponseEntity<List<WarehouseDto>> getWarehouses() {
        List<Warehouse> warehouses = getWarehousesUseCase.getWarehouses();
        List<WarehouseDto> warehouseDtos = new ArrayList<>();

        warehouses.forEach(warehouse -> warehouseDtos.add(createWarehouseDto(warehouse)));

        return ResponseEntity.ok(warehouseDtos);
    }

    @GetMapping("warehouse/{warehouseNumber}")
    public WarehouseDto getWarehouse(@PathVariable int warehouseNumber) {

        Warehouse warehouse = getWarehouseUseCase.getWarehouseByWarehouseNumber(new GetWarehouseCommand(warehouseNumber));
        Material material = getMaterialUseCase.getMaterial(new GetMaterialCommand(warehouse.getMaterialType()));
        Seller seller = getSellerUseCase.getSeller(new GetSellerCommand(warehouse.getSellerUUID()));

        return buildWarehouseDto(warehouse, material, seller);
    }

    private WarehouseDto createWarehouseDto(Warehouse warehouse) {
        Material material = getMaterialUseCase.getMaterial(new GetMaterialCommand(warehouse.getMaterialType()));
        Seller seller = getSellerUseCase.getSeller(new GetSellerCommand(warehouse.getSellerUUID()));

        return buildWarehouseDto(warehouse, material, seller);
    }

    private WarehouseDto buildWarehouseDto(Warehouse warehouse, Material material, Seller seller){

        List<PdtDto> pdtDtoList = warehouse.getPdtList().stream()
                .map(this::createPdtDto).toList();

        return new WarehouseDto(
                warehouse.getWareHouseNumber(),
                warehouse.calculateAndGetCurrentStock(),
                warehouse.getWarehousePercentageUtilization(),
                material.getDescription(),
                seller.getName(),
                pdtDtoList
        );
    }

    private PdtDto createPdtDto(Pdt pdt) {
        return new PdtDto(pdt.getTimeOfDelivery(), pdt.getAmountOfTonsDelivered());
    }
}
