package be.kdg.prog6.boundedcontextInvoice.adapters.out.ampq;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;
import be.kdg.prog6.boundedcontextInvoice.ports.out.UpdateInvoiceRecordPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEventPublisher implements UpdateInvoiceRecordPort {

    private final RabbitTemplate rabbitTemplate;

    public InvoiceEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updateInvoiceRecord(InvoiceRecord invoice, InvoiceAction invoiceAction) {

        if(invoiceAction.equals(InvoiceAction.REQUEST_PDTS)){

            InvoiceRecord.InvoiceUUID invoiceUUID = invoice.getInvoiceUUID();
            final String routingKey = "invoice. " + invoiceUUID+ " .request.pdts_for_invoice";
            final String exchangeName = "invoiceExchange";

            rabbitTemplate.convertAndSend(exchangeName, routingKey, "");
        }

    }
}