package be.kdg.prog6.boundedcontextLandside.ports.out;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.DailyCalendarJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoadAndCreateAppointmentPort {

    Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck);
    Optional<List<Appointment>> loadAppointmentsByDailyCalendarJpaEntityAndTime(DailyCalendarJpaEntity dailyCalendarJpaEntity, LocalDateTime localDateTime);
    void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity);
}
