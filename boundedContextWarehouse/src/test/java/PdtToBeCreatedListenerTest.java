import be.kdg.prog6.boundedcontextWarehouse.BoundedContextWarehouseApplication;
import be.kdg.prog6.boundedcontextWarehouse.adapters.in.web.PdtToBeCreatedListener;
import be.kdg.prog6.boundedcontextWarehouse.ports.in.PdtToBeCreatedProjector;
import be.kdg.prog6.common.events.PdtToBeCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BoundedContextWarehouseApplication.class)
@AutoConfigureMockMvc
public class PdtToBeCreatedListenerTest {

    @MockBean
    private PdtToBeCreatedProjector pdtToBeCreatedProjector;

    private PdtToBeCreatedListener pdtToBeCreatedListener;

    @BeforeEach
    public void setUp() {
        pdtToBeCreatedListener = new PdtToBeCreatedListener(pdtToBeCreatedProjector);
    }

    @Test
    public void testCreatePdt() {
        // Arrange
        int warehouseNumber = 1;
        LocalDateTime timeOfDelivery = LocalDateTime.now();
        UUID appointmentUUID = UUID.randomUUID();
        PdtToBeCreatedEvent event = new PdtToBeCreatedEvent(warehouseNumber, timeOfDelivery, appointmentUUID);

        // Act
        pdtToBeCreatedListener.createPdt(event);

        // Assert
        verify(pdtToBeCreatedProjector, times(1)).createPdt(warehouseNumber, timeOfDelivery, appointmentUUID);
    }


    @Configuration // Use @Configuration to indicate this class is a configuration class
    static class TestConfig {
        @Bean
        public RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry() {
            return new RabbitListenerEndpointRegistry();
        }
    }
}
