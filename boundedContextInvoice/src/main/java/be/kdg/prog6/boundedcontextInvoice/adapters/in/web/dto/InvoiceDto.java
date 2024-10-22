package be.kdg.prog6.boundedcontextInvoice.adapters.in.web.dto;

import java.util.List;

public class InvoiceDto {

    private final String sellerName;
    private final List<InvoiceLineDto> invoiceLinesDto;
    private final int totalAmountToPay;

    public InvoiceDto(String sellerName, List<InvoiceLineDto> invoiceLinesDto, int totalAmountToPay) {
        this.sellerName = sellerName;
        this.invoiceLinesDto = invoiceLinesDto;
        this.totalAmountToPay = totalAmountToPay;
    }

    public String getSellerName() {
        return sellerName;
    }

    public List<InvoiceLineDto> getInvoiceLinesDto() {
        return invoiceLinesDto;
    }

    public int getTotalAmountToPay() {
        return totalAmountToPay;
    }
}
