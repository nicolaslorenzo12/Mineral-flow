package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, UUID> {

    Optional<AppointmentJpaEntity> findAppointmentJpaEntityByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck);
}
