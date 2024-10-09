package be.kdg.prog6.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {

    @Bean
    Queue warehouseActivityCreatedQueue() {
        return new Queue("warehouse.activity_created", false);
    }

    @Bean
    Queue materialAddedToWarehouseQueue(){
        return new Queue("landside.material_added", false);
    }

    @Bean
    Queue pdtToBeCreatedQueue(){ return new Queue("landside.pdt_to_be_created", false);}

    @Bean
    Queue materialDispatchedToWarehouseQueue(){ return new Queue("waterside.material_dispatch", false);}

    @Bean
    Queue matchShipmentOrderAndPurchaseOrderQueue(){ return new Queue("match.shipment_order_and_purchase_order", false);}
    @Bean
    Queue matchedShipmentOrderAndPurchaseOrderQueue(){return new Queue("matched.shipment_order_and_purchase_order", false);}

    @Bean
    TopicExchange warehouseExchange() {
        return new TopicExchange("warehouseExchange");
    }
    @Bean
    TopicExchange landsideExchange() {
        return new TopicExchange("landsideExchange");
    }
    @Bean
    TopicExchange watersideExchange() { return new TopicExchange("watersideExchange");}

    @Bean
    Binding bindWarehouseExchangeToActivityCreatedQueue(
            Queue warehouseActivityCreatedQueue,
            TopicExchange warehouseExchange
    ) {
        return BindingBuilder
                .bind(warehouseActivityCreatedQueue)
                .to(warehouseExchange)
                .with("warehouse.#.activity_created");
    }

    @Bean
    Binding bindLandsideExchangeToMaterialAddedToWarehouseQueue(
            Queue materialAddedToWarehouseQueue,
            TopicExchange landsideExchange
    ) {
        return BindingBuilder
                .bind(materialAddedToWarehouseQueue)
                .to(landsideExchange)
                .with("landside.#.material_added");
    }


    @Bean
    Binding bindLandsideExchangeToPdtToBeCreatedQueue(
            Queue pdtToBeCreatedQueue,
            TopicExchange landsideExchange
    ) {
        return BindingBuilder
                .bind(pdtToBeCreatedQueue)
                .to(landsideExchange)
                .with("landside.#.pdt_to_be_created");
    }

    @Bean
    Binding bindWatersideExchangeToMaterialDispatchedToWarehouseQueue(
            Queue materialDispatchedToWarehouseQueue,
            TopicExchange watersideExchange
    ) {
        return BindingBuilder
                .bind(materialDispatchedToWarehouseQueue)
                .to(watersideExchange)
                .with("waterside.#.material_dispatch");
    }

    @Bean
    Binding bindWatersideExchangeToMatchShipmentOrderAndPurchaseOrderQueue(
            Queue matchShipmentOrderAndPurchaseOrderQueue,
            TopicExchange watersideExchange
    ) {
        return BindingBuilder
                .bind(matchShipmentOrderAndPurchaseOrderQueue)
                .to(watersideExchange)
                .with("match.#.shipment_order_and_purchase_order");
    }

    @Bean
    Binding bindWarehouseExchangeToMatchedShipmentOrderAndPurchaseOrderQueue(
            Queue matchedShipmentOrderAndPurchaseOrderQueue,
            TopicExchange warehouseExchange
    ) {
        return BindingBuilder
                .bind(matchedShipmentOrderAndPurchaseOrderQueue)
                .to(warehouseExchange)
                .with("matched.#.shipment_order_and_purchase_order");
    }



    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    // Configure Jackson JSON message converter for message serialization
//    @Bean
//    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Register Java Time Module
            return new Jackson2JsonMessageConverter(objectMapper);
    }
}
