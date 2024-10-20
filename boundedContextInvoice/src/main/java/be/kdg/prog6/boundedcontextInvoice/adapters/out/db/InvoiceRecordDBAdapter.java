package be.kdg.prog6.boundedcontextInvoice.adapters.out.db;

import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceAction;
import be.kdg.prog6.boundedcontextInvoice.domain.InvoiceRecord;
import be.kdg.prog6.boundedcontextInvoice.ports.out.LoadInvoiceRecordPort;
import be.kdg.prog6.boundedcontextInvoice.ports.out.UpdateInvoiceRecordPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InvoiceRecordDBAdapter implements UpdateInvoiceRecordPort, LoadInvoiceRecordPort {

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

    @Override
    public List<InvoiceRecord> loadInvoiceRecordsBySellerUUIDAndDate(Seller.CustomerUUID sellerUUID, LocalDate date) {

        List<InvoiceRecordJpaEntity> invoiceRecordJpaEntities =
                invoiceRecordJpaEntityRepository.findInvoiceRecordJpaEntitiesBySellerUUIDAndInvoiceDate(sellerUUID.uuid(), date);

        return invoiceRecordJpaEntities.stream()
                .map(this::buildInvoiceRecordObject).toList();

    }

    private InvoiceRecord buildInvoiceRecordObject(InvoiceRecordJpaEntity invoiceRecordJpaEntity) {

        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(invoiceRecordJpaEntity.getSellerUUID());
        InvoiceRecord.InvoiceUUID invoiceUUID = new InvoiceRecord.InvoiceUUID(invoiceRecordJpaEntity.getInvoiceUUID());

        return new InvoiceRecord(sellerUUID, invoiceRecordJpaEntity.getAmountOfTons(),
                invoiceRecordJpaEntity.getPdtCreationDate(), invoiceRecordJpaEntity.getInvoiceDate(),
                invoiceRecordJpaEntity.getMaterialType(), invoiceUUID);
    }
}

