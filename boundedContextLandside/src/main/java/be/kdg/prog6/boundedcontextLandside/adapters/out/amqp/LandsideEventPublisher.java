package be.kdg.prog6.boundedcontextLandside.adapters.out.amqp;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.events.MaterialAddedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LandsideEventPublisher implements UpdateDailyCalendarPort {

    private final RabbitTemplate rabbitTemplate;
    public LandsideEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void updateAppointment(Appointment appointment, DailyCalendar dailyCalendar) {
        if (appointment.getTruckStatus().equals(TruckStatus.LEFT)) {
            final String routingKey = "landside. " + appointment.getWarehouseNumber() + " .material_added";
            final String exchangeName = "landsideExchange";
            final MaterialAddedEvent body = new MaterialAddedEvent(appointment.getInitialWeight(),appointment.getFinalWeight(), appointment.getWarehouseNumber());

            rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
        }

    }
}
