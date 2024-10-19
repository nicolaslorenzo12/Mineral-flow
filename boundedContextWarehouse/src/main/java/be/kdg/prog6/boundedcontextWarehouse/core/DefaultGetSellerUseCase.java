package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetSellerCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetSellerUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DefaultGetSellerUseCase implements GetSellerUseCase {

    private final LoadSellerPort loadSellerPort;

    public DefaultGetSellerUseCase(LoadSellerPort loadSellerPort) {
        this.loadSellerPort = loadSellerPort;
    }

    @Override
    public Seller getSeller(GetSellerCommand getSellerCommand) {
        return loadSellerPort.loadSellerBySellerUUID(new Seller.CustomerUUID(getSellerCommand.sellerUUID().uuid())).
                orElseThrow(() -> new NoSuchElementException("Seller not found"));
    }
}
