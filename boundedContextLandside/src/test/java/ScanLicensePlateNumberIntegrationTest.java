import be.kdg.prog6.boundedcontextLandside.adapters.out.db.AppointmentJpaEntity;
import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarRepository;
import be.kdg.prog6.boundedcontextLandside.core.DefaultScanLicensePlateNumberWhenArrivingUseCase;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingCommand;
import be.kdg.prog6.common.domain.MaterialType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

//This annotation is used to create an integration test that will start the full application context
@SpringBootTest
//This tells spring where exactly to look for the tools I need for the test. Without this it might not find the beans I need for the tests
@ContextConfiguration(classes = be.kdg.prog6.boundedcontextLandside.BoundedContextLandsideApplication.class)
//Spring starts a transaction before every test starts and roll it back after the method completes. That is why I do not need an after each method
@Transactional
class ScanLicensePlateNumberIntegrationTest {

    @Autowired
    private DefaultScanLicensePlateNumberWhenArrivingUseCase defaultScanLicensePlateNumberWhenArrivingUseCase;
    @Autowired
    private DailyCalendarRepository dailyCalendarRepository;
    private ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand;
    private DailyCalendarJpaEntity dailyCalendarJpaEntity;
    private AppointmentJpaEntity appointmentJpaEntity;


    @BeforeEach
    void setup() {

        scanLicensePlateNumberCommand = new ScanLicensePlateNumberWhenArrivingCommand("ABC123");

        // Appointment
        appointmentJpaEntity = new AppointmentJpaEntity(
                UUID.randomUUID(),
                UUID.randomUUID(),
                1,
                LocalDateTime.now().withMinute(0).withSecond(0).withNano(0),
                MaterialType.GP,
                "ABC123",
                TruckStatus.NOTARRIVED,
                5,
                LocalDate.now(),
                0,
                0,
                null,
                null
        );


        dailyCalendarJpaEntity = new DailyCalendarJpaEntity(LocalDate.now());
        dailyCalendarJpaEntity.getAppointments().add(appointmentJpaEntity);
        appointmentJpaEntity.setDailyCalendarJpaEntity(dailyCalendarJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }



    @Test
    void shouldSuccessfullyScanLicensePlateAndUpdateAppointment() {
        // Act
        Appointment appointmentResult = defaultScanLicensePlateNumberWhenArrivingUseCase.scanLicensePlateNumber(scanLicensePlateNumberCommand);

        // Assert
        assertNotNull(appointmentResult);
        assertEquals("ABC123", appointmentResult.getLicensePlateNumberOfTruck());
        assertEquals(TruckStatus.ARRIVED, appointmentResult.getTruckStatus());
        assertNotNull(appointmentResult.getArrivalTime());
    }



    @Test
    void shouldThrowExceptionWhenAppointmentNotFound() {
        // Arrange: No matching appointment for a different license plate
        scanLicensePlateNumberCommand = new ScanLicensePlateNumberWhenArrivingCommand("AAA123");

        // Act & Assert
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> defaultScanLicensePlateNumberWhenArrivingUseCase.scanLicensePlateNumber(scanLicensePlateNumberCommand)
        );

        // Assert
        assertEquals("Appointment was not found", exception.getMessage());
    }

    @Test
    void shouldNotUpdateDailyCalendarWhenTruckAlreadyArrived() {
        // Arrange
        appointmentJpaEntity.setTruckStatus(TruckStatus.WEIGHTINGFIRSTTIME);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            defaultScanLicensePlateNumberWhenArrivingUseCase.scanLicensePlateNumber(scanLicensePlateNumberCommand);
        });

        //Assert
        assertEquals("The truck's status transitions must follow the correct order. Please ensure all necessary processes have been completed before moving to the next status.",
                exception.getMessage());
        assertNotEquals(TruckStatus.NOTARRIVED, appointmentJpaEntity.getTruckStatus());
        assertTrue(appointmentJpaEntity.getTruckStatus().getCode() >= TruckStatus.ARRIVED.getCode(),
                "Truck status should be ARRIVED or higher.");
    }
}
