package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.*;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultMakeAppointmentUseCase implements MakeAppointmentUseCase {

    private final LoadOrCreateWarehousePort loadOrCreateWarehousePort;
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultMakeAppointmentUseCase(LoadOrCreateWarehousePort loadOrCreateWarehousePort, LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    @Transactional
    public Appointment makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        Seller seller = makeAppointmentCommand.seller();
        Material material  = makeAppointmentCommand.material();
        LocalDateTime appointmentTime = makeAppointmentCommand.appointmentTime();
        final Warehouse warehouse = findWarehouseForSellerAndMaterial(seller, material);
        final DailyCalendar dailyCalendar = findDailyCalenderByDay(appointmentTime.toLocalDate());

        warehouse.checkIfMaximumStockPercentageExceeded();
        Appointment appointment = dailyCalendar.addAppointment(seller, appointmentTime, material, makeAppointmentCommand.licensePlateNumber(),
                warehouse);

        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));

        return appointment;
    }


    private DailyCalendar findDailyCalenderByDay(LocalDate day){
        return loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(day);
    }

    private Warehouse findWarehouseForSellerAndMaterial(Seller seller, Material material) {
        return loadOrCreateWarehousePort.loadWarehouseBySellerUUIDAndMaterialType(
                seller.getCustomerUUID().uuid(),
                material.getMaterialType()
        ).orElseThrow(() -> new NoSuchElementException("Warehouse not found, it should be created in advance"));
    }

}
