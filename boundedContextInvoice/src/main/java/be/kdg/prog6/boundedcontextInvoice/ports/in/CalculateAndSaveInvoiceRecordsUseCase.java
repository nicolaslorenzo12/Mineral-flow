package be.kdg.prog6.boundedcontextInvoice.ports.in;

public interface CalculateAndSaveInvoiceRecordsUseCase {

    void saveInvoiceRecords(CalculateAndSaveInvoiceRecordsCommand calculateAndSaveInvoiceRecordsCommand);
}
