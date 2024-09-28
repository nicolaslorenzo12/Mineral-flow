package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoadAndCreateAppointmentPort {

    Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck);
    Optional<List<Appointment>> loadAppointmentsByDailyCalendarJpaEntity(DailyCalendarJpaEntity dailyCalendarJpaEntity);
    void createAppointment(Appointment appointment);
}
