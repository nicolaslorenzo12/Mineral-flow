package be.kdg.prog6.boundedcontextWaterside.ports.in;

import be.kdg.prog6.boundedcontextWaterside.domain.ShipmentOrder;
import com.rabbitmq.client.Command;

public record RefuelShipCommand(String vesselNumber){
}
