package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.ports.out.UpdateInvoiceRecordPort;
import org.springframework.stereotype.Component;

@Component
public class InvoiceRecordDBAdapter implements UpdateInvoiceRecordPort {

    private final InvoiceRecordJpaEntityRepository invoiceRecordJpaEntityRepository;

    public InvoiceRecordDBAdapter(InvoiceRecordJpaEntityRepository invoiceRecordJpaEntityRepository) {
        this.invoiceRecordJpaEntityRepository = invoiceRecordJpaEntityRepository;
    }

    @Override
    public void updateInvoiceRecord(InvoiceRecord invoice, InvoiceAction invoiceAction) {

        if(invoiceAction.equals(InvoiceAction.CREATE_INVOICE_RECORD)){

            invoiceRecordJpaEntityRepository.save(buildInvoiceRecordJpaEntity(invoice));
        }
    }

    private InvoiceRecordJpaEntity buildInvoiceRecordJpaEntity(InvoiceRecord invoiceRecord) {

        return new InvoiceRecordJpaEntity(invoiceRecord.getInvoiceUUID().uuid(), invoiceRecord.getSellerUUID().uuid(),
                invoiceRecord.getPdtCreationDate(), invoiceRecord.getAmountOfTons(), invoiceRecord.getInvoiceDate(), invoiceRecord.getMaterialType());
    }
}