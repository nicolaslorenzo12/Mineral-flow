package be.kdg.prog6.boundedcontextLandside.adapters.out.amqp;

import be.kdg.prog6.boundedcontextLandside.domain.*;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.events.MaterialAddedEvent;
import be.kdg.prog6.common.events.PdtToBeCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class LandsideEventPublisher implements UpdateDailyCalendarPort, UpdateWarehousePort {

    private final RabbitTemplate rabbitTemplate;
    public LandsideEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updateDailyCalendar(DailyCalendar dailyCalendar, Appointment appointment) {

        if (appointment.getTruckStatus().equals(TruckStatus.LEFT)) {
            final String routingKey = "landside. " + appointment.getWarehouseNumber() + " .material_added";
            final String exchangeName = "landsideExchange";
            final MaterialAddedEvent body = new MaterialAddedEvent(appointment.getInitialWeight(),appointment.getFinalWeight(),
                    appointment.getWarehouseNumber(), appointment.getAppointmentUUID().uuid());

            rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
        }
    }

    @Override
    public void updateWarehouse(Warehouse warehouse, UpdateWarehouseAction updateWarehouseAction, UUID appointmentUUID, LocalDateTime timeOfDelivery) {

        if(updateWarehouseAction.equals(UpdateWarehouseAction.CREATE_PDT)) {

          final String routingKey = "landside. " + warehouse.getWareHouseNumber() + " .pdt_to_be_created";
          final String exchangeName = "landsideExchange";
          final PdtToBeCreatedEvent body = new PdtToBeCreatedEvent(warehouse.getWareHouseNumber(), timeOfDelivery, appointmentUUID);

            rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
        }
    }
}
