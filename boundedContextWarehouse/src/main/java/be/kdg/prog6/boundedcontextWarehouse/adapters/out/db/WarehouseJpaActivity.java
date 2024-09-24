package be.kdg.prog6.boundedcontextWarehouse.adapters.out.db;

import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
public class WarehouseJpaActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID sellerUUID;

    private int warehouseNumber;

    private int amountOfTons;

    @Enumerated(EnumType.STRING)
    private WarehouseAction warehouseAction;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID materialUUID;

    public UUID getUuid() {
        return uuid;
    }

    public UUID getSellerUUID() {
        return sellerUUID;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public int getAmountOfTons() {
        return amountOfTons;
    }

    public WarehouseAction getWarehouseAction() {
        return warehouseAction;
    }

    public UUID getMaterialUUID() {
        return materialUUID;
    }
}
