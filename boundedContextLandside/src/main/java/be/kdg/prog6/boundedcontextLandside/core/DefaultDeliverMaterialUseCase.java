package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.*;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateWarehousePort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public void deliverMaterial(DeliverMaterialCommand loadMaterialCommand) {

        Appointment appointment = findAppointmentByUUID(loadMaterialCommand.appointmentUUID());
        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.RECEIVE_MATERIAL);
        Warehouse warehouse = findWarehouseBySellerUUIDAndMaterialType(appointment.getSellerUUID(), appointment.getMaterialType());
        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateAppointment(appointment,new DailyCalendar(LocalDate.now())));
        updateWarehousePorts.forEach(updateWarehousePort -> updateWarehousePort.updateWarehouse(warehouse, UpdateWarehouseAction.CREATE_PDT));
    }

    private Appointment findAppointmentByUUID(Appointment.AppointmentUUID appointmentUUID){

        return loadDailyCalendarPort.loadAppointmentByAppointmentUUID(appointmentUUID, new DailyCalendar(LocalDate.now()))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Appointment was not found"));
    }

    private Warehouse findWarehouseBySellerUUIDAndMaterialType(Seller.CustomerUUID sellerUUID, MaterialType materialType){
        return loadOrCreateWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(sellerUUID.uuid(), materialType)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse was not found"));
    }
}
