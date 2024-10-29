package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(catalog = "Landside")
public class DailyCalendarJpaEntity {

    @Id
    private LocalDate day;
    @OneToMany(mappedBy = "dailyCalendarJpaEntity", cascade = CascadeType.ALL)
    private List<AppointmentJpaEntity> appointments;

    public DailyCalendarJpaEntity(LocalDate day) {
        this.day = day;
        appointments = new ArrayList<>();
    }

    public DailyCalendarJpaEntity() {
        this.appointments = new ArrayList<>();
    }

    public LocalDate getDay() {
        return day;
    }

    public List<AppointmentJpaEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentJpaEntity> appointments) {
        this.appointments = appointments;
    }
}
