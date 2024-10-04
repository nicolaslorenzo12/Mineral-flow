package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DailyCalendar {

    private final LocalDate day;
    private List<Appointment> appointments;


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

    public void checkIfThereAreMoreThan40AppointmentsAndIfYesThrowException(List<Appointment> appointmentsAtCertainHour){
        if(appointmentsAtCertainHour.size() == 40){
            throw new CustomException(HttpStatus.CONFLICT, "The limit of 40 appointments per hour has been reached");
        }
    }

    public List<Appointment> filterAppointmentsByAppointmentTime(LocalDateTime appointmentTime){
        int targetHour = appointmentTime.getHour();

        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentTime().getHour() == targetHour)
                .collect(Collectors.toList());
    }

    public Appointment findAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID){
        return this.getAppointments().stream().filter(appointment -> appointment.getAppointmentUUID().equals(appointmentUUID))
                .findFirst().orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Appointment was not found"));
    }

    public Appointment findAppointmentByLicensePlateNumberAndTimeAndDay(String licensePlateNumber,LocalDateTime roundedTime){

        return appointments.stream().filter(appointment -> appointment.getLicensePlateNumberOfTruck().equals(licensePlateNumber)
        && appointment.getAppointmentTime().equals(roundedTime)
        && appointment.getDay().equals(day)).findFirst().orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Appointment was not found"));
    }


    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }
}
