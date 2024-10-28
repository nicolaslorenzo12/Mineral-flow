package be.kdg.prog6.boundedcontextWarehouse.adapters.in.ampq;

import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeCreatedProjector;
import be.kdg.prog6.common.events.PdtToBeCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PdtToBeCreatedListener {

    private final PdtToBeCreatedProjector pdtToBeCreatedProjector;

    public PdtToBeCreatedListener(PdtToBeCreatedProjector pdtToBeCreatedProjector) {
        this.pdtToBeCreatedProjector = pdtToBeCreatedProjector;
    }

    @RabbitListener(queues = "landside.pdt_to_be_created")
    public void createPdt(final PdtToBeCreatedEvent pdtToBeCreatedEvent){

        pdtToBeCreatedProjector.createPdt(pdtToBeCreatedEvent.warehouseNumber(), pdtToBeCreatedEvent.timeOfDelivery(), pdtToBeCreatedEvent.appointmentUUID());
    }
}
