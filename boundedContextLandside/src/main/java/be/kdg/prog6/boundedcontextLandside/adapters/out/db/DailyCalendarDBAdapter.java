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
    public DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(localDate).
                orElseGet(() -> createNewDailyCalendar(localDate));

        return new DailyCalendar(dailyCalendarJpaEntity.getDay());
    }

    private DailyCalendarJpaEntity createNewDailyCalendar(LocalDate localDate){
        return new DailyCalendarJpaEntity(localDate);
    }
}

