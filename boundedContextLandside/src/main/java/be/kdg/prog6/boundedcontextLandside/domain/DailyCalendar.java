package be.kdg.prog6.boundedcontextLandside.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DailyCalendar {

    public LocalDate day;
    List<Appointment> appointment;
    private DailyCalendarUUID dailyCalendarUUID;
    public record DailyCalendarUUID(UUID uuid){

    }
}
