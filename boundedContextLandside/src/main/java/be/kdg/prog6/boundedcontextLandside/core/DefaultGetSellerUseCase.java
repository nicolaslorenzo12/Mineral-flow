package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.ports.in.GetSellerByNameCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetSellerByUUIDCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.GetSellerUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("landsideDefaultGetSellerUseCase")
public class DefaultGetSellerUseCase implements GetSellerUseCase {

    private final LoadSellerPort loadSellerPort;

    public DefaultGetSellerUseCase(LoadSellerPort loadSellerPort) {
        this.loadSellerPort = loadSellerPort;
    }

    @Override
    public Seller getSellerBySellerUUID(GetSellerByUUIDCommand getSellerCommand) {
        return loadSellerPort.loadSellerByUUID(getSellerCommand.sellerUUID().uuid())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Seller not found"));
    }

    @Override
    public Seller getSellerByName(GetSellerByNameCommand getSellerCommand) {
        return loadSellerPort.loadSellerByName(getSellerCommand.name())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Seller not found"));
    }
}
