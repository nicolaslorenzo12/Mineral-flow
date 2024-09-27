package be.kdg.prog6.messaging;

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

    // Define a queue for warehouse activity creation events
    @Bean
    Queue warehouseActivityCreatedQueue() {
        return new Queue("warehouse.activity_created", false);
    }

    // Define a topic exchange for the warehouse context
    @Bean
    TopicExchange warehouseExchange() {
        return new TopicExchange("warehouseExchange");
    }

    // Bind the warehouse activity created queue to the warehouse exchange with the appropriate routing key
    @Bean
    Binding bindWarehouseExchangeToActivityCreatedQueue(
            Queue warehouseActivityCreatedQueue,
            TopicExchange warehouseExchange
    ) {
        return BindingBuilder
                .bind(warehouseActivityCreatedQueue)
                .to(warehouseExchange)
                .with("warehouse.#.activity_created"); // Use wildcard to capture all warehouse activity created events
    }

    // Configure the RabbitTemplate with a ConnectionFactory
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    // Configure Jackson JSON message converter for message serialization
    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
