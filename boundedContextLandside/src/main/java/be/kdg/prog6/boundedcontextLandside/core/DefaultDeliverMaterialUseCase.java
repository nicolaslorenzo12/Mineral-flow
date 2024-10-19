package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.*;
import be.kdg.prog6.boundedcontextLandside.domain.dto.LoadedMaterialDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultDeliverMaterialUseCase implements DeliverMaterialUseCase {
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;
    private final List<UpdateWarehousePort> updateWarehousePorts;
    private final LoadOrCreateWarehousePort loadOrCreateWarehousePort;

    public DefaultDeliverMaterialUseCase(LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts,
                                         List<UpdateWarehousePort> updateWarehousePorts, LoadOrCreateWarehousePort loadOrCreateWarehousePort) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
        this.updateWarehousePorts = updateWarehousePorts;
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
    }
    @Override
    public LoadedMaterialDto deliverMaterial(DeliverMaterialCommand loadMaterialCommand) {

        DailyCalendar dailyCalendar = loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(LocalDate.now());

        Appointment appointment = dailyCalendar.receiveMaterialThroughAppointment(loadMaterialCommand.appointmentUUID());
        Warehouse warehouse = findWarehouseBySellerUUIDAndMaterialType(appointment.getSellerUUID(), appointment.getMaterialType());

        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));
        updateWarehousePorts.forEach(updateWarehousePort -> updateWarehousePort.updateWarehouse(warehouse,
                UpdateWarehouseAction.CREATE_PDT, appointment.getAppointmentUUID().uuid(), LocalDateTime.now()));

        return new LoadedMaterialDto(appointment.getLicensePlateNumberOfTruck(),
                LocalDateTime.now(), appointment.getWarehouseNumber());
    }

    private Warehouse findWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType){
        return loadOrCreateWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(sellerUUID.uuid(), materialType)
                .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
    }
}
