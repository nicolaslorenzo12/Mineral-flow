package be.kdg.prog6.boundedcontextInvoice.adapters.in.ampq;

import be.kdg.prog6.boundedcontextInvoice.ports.in.SaveInvoiceRecordsCommand;
import be.kdg.prog6.boundedcontextInvoice.ports.in.SaveInvoiceRecordsUseCase;
import be.kdg.prog6.common.commands.AllPdtToSendForInvoiceCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AllPdtForInvoiceRecordListener {

    private final SaveInvoiceRecordsUseCase calculateAndSaveInvoiceRecordsUseCase;

    public AllPdtForInvoiceRecordListener(SaveInvoiceRecordsUseCase calculateAndSaveInvoiceRecordsUseCase) {
        this.calculateAndSaveInvoiceRecordsUseCase = calculateAndSaveInvoiceRecordsUseCase;
    }

    @RabbitListener(queues = "pdts_to_be_sent_for_invoice")
    public void receiveAllPdtToCalculateAndSaveInvoiceRecord(final AllPdtToSendForInvoiceCommand allPdtToSendForInvoiceCommand) {

        calculateAndSaveInvoiceRecordsUseCase.saveInvoiceRecords(new SaveInvoiceRecordsCommand
                (allPdtToSendForInvoiceCommand.allPdt()));
    }
}
