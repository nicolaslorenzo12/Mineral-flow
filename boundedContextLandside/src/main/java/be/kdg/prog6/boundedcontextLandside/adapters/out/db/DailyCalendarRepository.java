package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyCalendarRepository extends JpaRepository<DailyCalendarJpaEntity, LocalDate> {
    Optional<DailyCalendarJpaEntity> findDailyCalendarJpaEntityByDay(LocalDate localDate);
}
