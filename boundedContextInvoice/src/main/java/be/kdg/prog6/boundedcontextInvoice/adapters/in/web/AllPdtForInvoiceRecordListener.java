package be.kdg.prog6.boundedcontextInvoice.adapters.in.web;

import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateAndSaveInvoiceRecordsCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.CalculateAndSaveInvoiceRecordsUseCase;
import be.kdg.prog6.common.facades.AllPdtToSendForInvoiceCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AllPdtForInvoiceRecordListener {

    private final CalculateAndSaveInvoiceRecordsUseCase calculateAndSaveInvoiceRecordsUseCase;

    public AllPdtForInvoiceRecordListener(CalculateAndSaveInvoiceRecordsUseCase calculateAndSaveInvoiceRecordsUseCase) {
        this.calculateAndSaveInvoiceRecordsUseCase = calculateAndSaveInvoiceRecordsUseCase;
    }

    @RabbitListener(queues = "pdts_to_be_sent_for_invoice")
    public void receiveAllPdtToCalculateAndSaveInvoiceRecord(final AllPdtToSendForInvoiceCommand allPdtToSendForInvoiceCommand) {

        calculateAndSaveInvoiceRecordsUseCase.saveInvoiceRecords(new CalculateAndSaveInvoiceRecordsCommand
                (allPdtToSendForInvoiceCommand.allPdt(), allPdtToSendForInvoiceCommand.materials()));
    }
}
