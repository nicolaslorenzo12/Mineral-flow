package be.kdg.prog6.boundedcontextWarehouse.core;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetSellerCommand;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.GetSellerUseCase;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DefaultGetSellerUseCase implements GetSellerUseCase {

    private final LoadSellerPort loadSellerPort;

    public DefaultGetSellerUseCase(LoadSellerPort loadSellerPort) {
        this.loadSellerPort = loadSellerPort;
    }

    @Override
    public Seller getSeller(GetSellerCommand getSellerCommand) {
        return loadSellerPort.loadSellerBySellerUUID(new Seller.CustomerUUID(getSellerCommand.sellerUUID().uuid())).
                orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Seller not found"));
    }
}
