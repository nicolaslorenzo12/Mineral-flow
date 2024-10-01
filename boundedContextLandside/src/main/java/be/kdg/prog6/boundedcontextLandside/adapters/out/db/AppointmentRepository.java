package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    Optional<AppointmentJpaEntity> findAppointmentJpaEntityByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime appointmentTime, LocalDate localDate);

    Optional<AppointmentJpaEntity> findAppointmentJpaEntityByAppointmentUUID(UUID appointmentUuid);
}


