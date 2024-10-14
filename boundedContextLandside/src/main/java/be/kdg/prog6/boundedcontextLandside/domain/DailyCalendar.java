package be.kdg.prog6.boundedcontextLandside.domain;

import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
        && appointment.getDay().equals(day)).findFirst().orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,
                "Appointment was not found"));
    }


    public Appointment addAppointment(Seller seller, LocalDateTime appointmentTime, Material material, String licensePlateNumber,
                               Warehouse warehouse){

        List<Appointment> appointmentsAtACertainHour = this.filterAppointmentsByAppointmentTime(appointmentTime);
        this.checkIfThereAreMoreThan40AppointmentsAndIfYesThrowException(appointmentsAtACertainHour);
        int gateNumber = generateRandomGateNumber();
        Appointment appointment =  buildAppointmentObject(seller, appointmentTime, material, licensePlateNumber, warehouse, gateNumber);
        appointments.add(appointment);
        return appointment;
    }

    private int generateRandomGateNumber () {
        return (int) (Math.random() * 10) + 1;
    }

    private Appointment buildAppointmentObject(Seller seller, LocalDateTime appointmentTime, Material material, String licensePlateNumber,
                                               Warehouse warehouse, int gateNumber){

        return new Appointment(new Appointment.AppointmentUUID(UUID.randomUUID()),
                seller.getCustomerUUID(), this.day, gateNumber, appointmentTime,
                material.getMaterialType(), licensePlateNumber, TruckStatus.NOTARRIVED,
                warehouse.getWareHouseNumber(), 0, 0, null, null);
    }


    public Appointment setArrivalTimeOfAnAppointment(String licensePlateNumber, LocalDateTime arrivalTime){

        Appointment appointment = findAppointmentByLicensePlateNumberAndTimeAndDay(licensePlateNumber, arrivalTime);
        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.ARRIVED);
        appointment.setArrivalTime(LocalDateTime.now());
        return appointment;
    }

    public Appointment weightTruckOfAnAppointment(Appointment.AppointmentUUID appointmentUUID ){

        Appointment appointment = findAppointmentByAppointmentUUID(appointmentUUID);
        appointment.proccessWeighting();
        return appointment;
    }


    public Appointment receiveMaterialThroughAppointment(Appointment.AppointmentUUID appointmentUUID){
        findAppointmentByAppointmentUUID(appointmentUUID);
        Appointment appointment = findAppointmentByAppointmentUUID(appointmentUUID);
        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.RECEIVE_MATERIAL);
        return appointment;
    }

    public List<Appointment> getNumberOfAppointmentsAtACertainHourThatAreInside(LocalDateTime localDateTime){

        int targetHour = localDateTime.getHour();

        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentTime().getHour() == targetHour)
                .filter(appointment -> appointment.getTruckStatus().ordinal() >= TruckStatus.ARRIVED.ordinal())
                .toList();
    }
}
