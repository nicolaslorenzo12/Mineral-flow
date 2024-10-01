package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
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
    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final LoadDailyCalendarPort loadDailyCalendarPort;

    public DefaultMakeAppointmentUseCase(LoadSellerPort loadSellerPort, LoadMaterialPort loadMaterialPort, LoadOrCreateWarehousePort loadOrCreateWarehousePort,
                                         LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, LoadDailyCalendarPort loadDailyCalendarPort) {
        this.loadSellerPort = loadSellerPort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadOrCreateWarehousePort = loadOrCreateWarehousePort;
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.loadDailyCalendarPort = loadDailyCalendarPort;
    }

    @Override
    @Transactional
    public void makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        final UUID sellerUUID = makeAppointmentCommand.sellerUUID().uuid();
        final MaterialType materialType = makeAppointmentCommand.materialType();
        final Seller seller = findSellerByUUID(sellerUUID);
        final Material material = findMaterialByType(materialType);
        final Warehouse warehouse = findWarehouseForSellerAndMaterial(seller, material);
        final DailyCalendar dailyCalendar = findDailyCalenderByDay(makeAppointmentCommand.appointmentTime().toLocalDate());

        List<Appointment> appointments= dailyCalendar.filterAppointmentsByAppointmentTime(makeAppointmentCommand.appointmentTime());
        warehouse.checkIfMaximumStockPercentageExceeded();
        dailyCalendar.checkIfThereAreMoreThan40AppointmentsAndIfYesThrowException(appointments);
        int gateNumber = generateRandomGateNumber();

        Appointment appointment = buildAppointmentObject(seller, gateNumber, makeAppointmentCommand, material, warehouse, dailyCalendar.getDay());
        loadDailyCalendarPort.createAppointment(appointment, dailyCalendar);
//        loadAndCreateAppointmentPort.createAppointment(appointment, dailyCalendar);
    }

    private Appointment buildAppointmentObject(Seller seller, int gateNumber, MakeAppointmentCommand makeAppointmentCommand, Material material, Warehouse warehouse, LocalDate day){

        return new Appointment(new Appointment.AppointmentUUID(UUID.randomUUID()),
                seller.getCustomerUUID(), day, gateNumber, makeAppointmentCommand.appointmentTime(),
                material.getMaterialType(), makeAppointmentCommand.licensePlateNumber(), TruckStatus.NOTARRIVED,
                warehouse.getWareHouseNumber());
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
        );
    }
    private int generateRandomGateNumber () {
        return (int) (Math.random() * 10) + 1;
    }
}
