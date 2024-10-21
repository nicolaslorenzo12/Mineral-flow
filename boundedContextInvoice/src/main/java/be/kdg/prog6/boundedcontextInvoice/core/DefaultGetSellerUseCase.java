package be.kdg.prog6.boundedcontextInvoice.core;

import be.kdg.prog6.boundedcontextInvoice.ports.in.GetSellerCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.GetSellerUseCase;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("defaultGetSellerUseCaseInvoice")
public class DefaultGetSellerUseCase implements GetSellerUseCase {

    private final LoadSellerPort loadSellerPort;

    public DefaultGetSellerUseCase(LoadSellerPort loadSellerPort) {
        this.loadSellerPort = loadSellerPort;
    }

    @Override
    public Seller getSellerBySellerUUID(GetSellerCommand getSellerCommand) {
        return loadSellerPort.loadSellerBySellerUUID(getSellerCommand.sellerUUID())
                .orElseThrow(()-> new NoSuchElementException("Seller was not found"));
    }
}
