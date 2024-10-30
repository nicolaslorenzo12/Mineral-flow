import be.kdg.prog6.boundedcontextLandside.core.DefaultMakeAppointmentUseCase;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultMakeAppointmentUseCaseStubbingTest {

    private DefaultMakeAppointmentUseCase makeAppointmentUseCase;
    private StubLoadOrCreateWarehousePort loadOrCreateWarehousePort;
    private StubLoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private StubUpdateDailyCalendarPort updateDailyCalendarPort1;
    private StubUpdateDailyCalendarPort updateDailyCalendarPort2;
    private StubDailyCalendar dailyCalendar;
    private StubWarehouse warehouse;

    @BeforeEach
    void setUp() {
        loadOrCreateWarehousePort = spy(new StubLoadOrCreateWarehousePort());
        loadDailyCalendarPort = spy(new StubLoadOrCreateDailyCalendarPort());
        updateDailyCalendarPort1 = spy(new StubUpdateDailyCalendarPort());
        updateDailyCalendarPort2 = spy(new StubUpdateDailyCalendarPort());
        makeAppointmentUseCase = new DefaultMakeAppointmentUseCase(
                loadOrCreateWarehousePort, loadDailyCalendarPort, List.of(updateDailyCalendarPort1, updateDailyCalendarPort2)
        );
        dailyCalendar = new StubDailyCalendar(new ArrayList<>());
    }

    @Test
    void testMakeAppointment() {
        // Arrange
        warehouse =  new StubWarehouse(5,
                new Seller.CustomerUUID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")), MaterialType.GP);
        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Seller seller = new Seller(sellerUUID, "Nicolas");

        Material material = new Material(MaterialType.GP, "Gypsum");

        LocalDateTime appointmentTime = LocalDateTime.now();

        loadDailyCalendarPort.setDailyCalendar(dailyCalendar);
        loadOrCreateWarehousePort.setWarehouse(warehouse);

        MakeAppointmentCommand command = new MakeAppointmentCommand(seller, material,"ABC123", appointmentTime);

        int initialAppointmentsOfDailyCalendarSize = dailyCalendar.getAppointmentList().size();

        // Act
        Appointment appointment = makeAppointmentUseCase.makeAppointment(command);

        // Assert
        assertNotNull(appointment);
        assertEquals("ABC123", appointment.getLicensePlateNumberOfTruck());
        assertEquals(appointmentTime, appointment.getAppointmentTime());
        assertEquals(seller.getCustomerUUID(), appointment.getSellerUUID());
        assertEquals(material.getMaterialType(), appointment.getMaterialType());
        assertEquals(warehouse.getWareHouseNumber(), appointment.getWarehouseNumber());
        assertEquals(dailyCalendar.getAppointmentList().size(), initialAppointmentsOfDailyCalendarSize + 1);

        // Verify
        verify(updateDailyCalendarPort1, times(1)).updateDailyCalendar(dailyCalendar, appointment);
        verify(updateDailyCalendarPort2, times(1)).updateDailyCalendar(dailyCalendar, appointment);
        verify(loadOrCreateWarehousePort, times(1)).loadWarehouseBySellerUUIDAndMaterialType(sellerUUID.uuid(), material.getMaterialType());
    }

    @Test
    void testWarehouseNotFound() {
        // Arrange

        // This has a different sellerUUID. I am pretending that this warehouse does not exist so I can test the sad path
        warehouse =  new StubWarehouse(5,
                new Seller.CustomerUUID(UUID.fromString("123e4567-e89b-12d3-a456-426614174001")), MaterialType.GP);
        Seller.CustomerUUID sellerUUID = new Seller.CustomerUUID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Seller seller = new Seller(sellerUUID, "Nicolas");

        Material material = new Material(MaterialType.GP, "Gypsum");

        LocalDateTime appointmentTime = LocalDateTime.now();

        loadOrCreateWarehousePort.setWarehouse(warehouse);

        MakeAppointmentCommand command = new MakeAppointmentCommand(seller, material,"ABC123", appointmentTime);


        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            makeAppointmentUseCase.makeAppointment(command);
        });

        // Assert
        assertEquals("Warehouse not found, it should be created in advance", exception.getMessage());
    }
}
