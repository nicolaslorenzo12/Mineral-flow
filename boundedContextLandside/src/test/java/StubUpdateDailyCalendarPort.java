import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;

public class StubUpdateDailyCalendarPort  implements UpdateDailyCalendarPort {

    public static int callCounter = 0;
    @Override
    public void updateDailyCalendar(DailyCalendar dailyCalendar, Appointment appointment) {

        callCounter++;
        System.out.println(callCounter + " update(s)");
    }
}
