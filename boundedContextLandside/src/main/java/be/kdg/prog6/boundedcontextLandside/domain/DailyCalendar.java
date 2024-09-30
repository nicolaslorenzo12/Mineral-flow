package be.kdg.prog6.boundedcontextLandside.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyCalendar {

    private final LocalDate day;
    private List<Appointment> appointments;

    public DailyCalendar(LocalDate day) {
        this.day = day;
        appointments = new ArrayList<>();
    }

    public DailyCalendar(LocalDate day, List<Appointment> appointments) {
        this.day = day;
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public LocalDate getDay() {
        return day;
    }
}
