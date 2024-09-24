package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
public class WarehouseJpaEntity {

    @Id
    private int wareHouseNumber;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID sellerUUID;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID materialUUID;

    public WarehouseJpaEntity(int wareHouseNumber, UUID sellerUUID, UUID materialUUID) {
        this.wareHouseNumber = wareHouseNumber;
        this.sellerUUID = sellerUUID;
        this.materialUUID = materialUUID;
    }

    public WarehouseJpaEntity() {

    }

    public int getWareHouseNumber() {
        return wareHouseNumber;
    }

    public void setWareHouseNumber(int wareHouseNumber) {
        this.wareHouseNumber = wareHouseNumber;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public void setSellerUUID(UUID sellerUUID) {
        this.sellerUUID = sellerUUID;
    }

    public UUID getMaterialUUID() {
        return materialUUID;
    }

    public void setMaterialUUID(UUID materialUUID) {
        this.materialUUID = materialUUID;
    }
}
