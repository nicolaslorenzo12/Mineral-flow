package be.kdg.prog6.boundedcontextLandside.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DailyCalendar {

    public final LocalDate day;
    List<Appointment> appointment;
    private final DailyCalendarUUID dailyCalendarUUID;
    public DailyCalendar(DailyCalendarUUID dailyCalendarUUID, LocalDate day) {
        this.dailyCalendarUUID = dailyCalendarUUID;
        this.day = day;
    }

    public record DailyCalendarUUID(UUID uuid){

    }
}
