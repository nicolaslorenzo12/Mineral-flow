package be.kdg.prog6.boundedcontextWarehouse.adapters.in.web;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeRequestedForInvoiceUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
