package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<AppointmentJpaEntity> getAppointments() {
        return appointments;
    }

    public void addAppointment(AppointmentJpaEntity appointmentJpaEntity){
        appointments.add(appointmentJpaEntity);
    }

    public void setAppointments(List<AppointmentJpaEntity> appointments) {
        this.appointments = appointments;
    }
}
