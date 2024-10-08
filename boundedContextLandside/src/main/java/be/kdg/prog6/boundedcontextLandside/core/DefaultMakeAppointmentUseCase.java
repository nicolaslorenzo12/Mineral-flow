package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.domain.dto.CreatedAppointmentDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultMakeAppointmentUseCase implements MakeAppointmentUseCase {
    private final LoadSellerPort loadSellerPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadOrCreateWarehousePort loadOrCreateWarehousePort;
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultMakeAppointmentUseCase(LoadSellerPort loadSellerPort, LoadMaterialPort loadMaterialPort,
                                         LoadOrCreateWarehousePort loadOrCreateWarehousePort, LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadSellerPort = loadSellerPort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    @Transactional
    public CreatedAppointmentDto makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        final UUID sellerUUID = makeAppointmentCommand.sellerUUID().uuid();
        final MaterialType materialType = makeAppointmentCommand.materialType();
        final Seller seller = findSellerByUUID(sellerUUID);
        final Material material = findMaterialByType(materialType);
        final Warehouse warehouse = findWarehouseForSellerAndMaterial(seller, material);
        final DailyCalendar dailyCalendar = findDailyCalenderByDay(makeAppointmentCommand.appointmentTime().toLocalDate());

        warehouse.checkIfMaximumStockPercentageExceeded();
        Appointment appointment = dailyCalendar.addAppointment(seller, makeAppointmentCommand.appointmentTime(), material,
                makeAppointmentCommand.licensePlateNumber(), warehouse);

        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));

        return new CreatedAppointmentDto(appointment.getAppointmentUUID(), appointment.getSellerUUID(), appointment.getDay(),
                appointment.getGateNumber(), appointment.getAppointmentTime(), appointment.getMaterialType(),
                appointment.getLicensePlateNumberOfTruck(), appointment.getWarehouseNumber());
    }


    private Seller findSellerByUUID(UUID sellerUUID) {
        return loadSellerPort.loadSellerByUUID(sellerUUID)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Seller not found"));
    }
    private Material findMaterialByType(MaterialType materialType) {
        return loadMaterialPort.loadMaterialByMaterialType(materialType)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Material not found"));
    }

    private DailyCalendar findDailyCalenderByDay(LocalDate day){
        return loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(day);
    }

    private Warehouse findWarehouseForSellerAndMaterial(Seller seller, Material material) {
        return loadOrCreateWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(
                seller.getCustomerUUID().uuid(),
                material.getMaterialType()
        ).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Warehouse not found, it should be created in advance"));
    }
}
