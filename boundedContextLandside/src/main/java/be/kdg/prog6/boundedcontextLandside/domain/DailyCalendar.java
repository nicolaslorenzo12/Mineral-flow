package be.kdg.prog6.boundedcontextLandside.domain;

import java.time.LocalDate;
import java.util.List;

public class DailyCalendar {

    private final LocalDate day;
    private List<Appointment> appointments;

    public DailyCalendar(LocalDate day) {
        this.day = day;
    }


    public List<Appointment> getAppointments() {
        return appointments;
    }

    public LocalDate getDay() {
        return day;
    }
}
