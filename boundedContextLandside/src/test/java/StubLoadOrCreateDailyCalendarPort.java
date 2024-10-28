import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;

import java.time.LocalDate;

class StubLoadOrCreateDailyCalendarPort implements LoadOrCreateDailyCalendarPort {

    private StubDailyCalendar dailyCalendar;

    public void setDailyCalendar(StubDailyCalendar dailyCalendar) {
        this.dailyCalendar = dailyCalendar;
    }

    @Override
    public DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate date) {
        return dailyCalendar;
    }
}
