package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadDailyCalendarPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DailyCalendarDBAdapter implements LoadDailyCalendarPort {

    private final DailyCalendarRepository dailyCalendarRepository;

    public DailyCalendarDBAdapter(DailyCalendarRepository dailyCalendarRepository) {
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    @Override
    public Optional<DailyCalendar> loadDailyCalendarByDay(LocalDate localDate) {

        Optional<DailyCalendarJpaEntity> dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(localDate);

        if(dailyCalendarJpaEntity.isEmpty()){
            return Optional.empty();
        }

        DailyCalendar dailyCalendar = new DailyCalendar(dailyCalendarJpaEntity.get().getDay());
        return Optional.of(dailyCalendar);
    }
}
