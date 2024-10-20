package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeRequestedForInvoiceUseCase;
import be.kdg.prog6.common.domain.Pdt;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdtToBeRequestedListener {

    private final PdtToBeRequestedForInvoiceUseCase pdtToBeRequestedForInvoiceUseCase;

    public PdtToBeRequestedListener(PdtToBeRequestedForInvoiceUseCase pdtToBeRequestedForInvoiceUseCase) {
        this.pdtToBeRequestedForInvoiceUseCase = pdtToBeRequestedForInvoiceUseCase;
    }

    @RabbitListener(queues = "request.pdts_for_invoice")
    public void requestPdt() {

        pdtToBeRequestedForInvoiceUseCase.requestAllPdt();
    }
}
