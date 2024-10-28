import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.Warehouse;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class StubDailyCalendar extends DailyCalendar {

    private List<Appointment> appointmentList;

    public StubDailyCalendar(List<Appointment> appointmentList) {
        super(LocalDate.now(), new ArrayList<>());
        this.appointmentList = appointmentList;
    }

    public Appointment addAppointment(Seller seller, LocalDateTime appointmentTime, Material material,
                                      String licensePlateNumber, Warehouse warehouse) {

        Appointment appointment = new Appointment(seller.getCustomerUUID(), appointmentTime, material.getMaterialType(), licensePlateNumber,
                                                  warehouse.getWareHouseNumber());

        appointmentList.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
