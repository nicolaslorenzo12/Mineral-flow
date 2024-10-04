package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DailyCalendarDBAdapter implements LoadOrCreateDailyCalendarPort, UpdateDailyCalendarPort {

    private final DailyCalendarRepository dailyCalendarRepository;

    public DailyCalendarDBAdapter(DailyCalendarRepository dailyCalendarRepository) {
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    private List<Appointment> buildAppointmentObjects(List<AppointmentJpaEntity> appointmentJpaEntityList) {
        List<Appointment> appointments = new ArrayList<>();
        appointmentJpaEntityList.forEach(appointmentJpaEntity -> appointments.add(new Appointment(
                new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Customer.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getDailyCalendarJpaEntity().getDay(),
                appointmentJpaEntity.getGateNumber(),
                appointmentJpaEntity.getAppointmentTime(),
                appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(),
                appointmentJpaEntity.getTruckStatus(),
                appointmentJpaEntity.getWarehouseNumber(),
                appointmentJpaEntity.getInitialWeight(),
                appointmentJpaEntity.getFinalWeight(),
                appointmentJpaEntity.getArrivalTime(),
                appointmentJpaEntity.getDepartureTime()
        )));
        return appointments;
    }

    private DailyCalendar buildDailyCalendarObject(final DailyCalendarJpaEntity dailyCalendarJpaEntity){

        LocalDate day = dailyCalendarJpaEntity.getDay();
        List<AppointmentJpaEntity> appointmentJpaEntityList  = dailyCalendarJpaEntity.getAppointments();
        List<Appointment> appointments =  buildAppointmentObjects(appointmentJpaEntityList);
        return new DailyCalendar(day, appointments);
    }
    @Override
    public DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = findDailyCalendarJpaEntity(localDate);

        return buildDailyCalendarObject(dailyCalendarJpaEntity);
    }

    private DailyCalendarJpaEntity findDailyCalendarJpaEntity(LocalDate localDate){
        return dailyCalendarRepository.findDailyCalendarJpaEntityByDay(localDate).
                orElseGet(() -> createNewDailyCalendar(localDate));
    }

    private DailyCalendarJpaEntity createNewDailyCalendar(LocalDate localDate){

        DailyCalendarJpaEntity dailyCalendarJpaEntity = new DailyCalendarJpaEntity(localDate);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
        return dailyCalendarJpaEntity;
    }

    @Override
    public void updateDailyCalendar(DailyCalendar dailyCalendar, Appointment appt) {
        final DailyCalendarJpaEntity dailyCalendarJpaEntity = findDailyCalendarJpaEntity(dailyCalendar.getDay());
        List<AppointmentJpaEntity> appointmentJpaEntityList = new ArrayList<>();
        dailyCalendar.getAppointments().forEach(appointment -> {
            AppointmentJpaEntity appointmentJpaEntity = buildAppointmentJpaEntity(appointment, dailyCalendar.getDay());
            appointmentJpaEntityList.add(appointmentJpaEntity);
        });

        dailyCalendarJpaEntity.setAppointments(appointmentJpaEntityList);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    private AppointmentJpaEntity buildAppointmentJpaEntity(Appointment appointment, LocalDate day) {
        return new AppointmentJpaEntity(
                appointment.getAppointmentUUID().uuid(),
                appointment.getSellerUUID().uuid(),
                appointment.getGateNumber(),
                appointment.getAppointmentTime(),
                appointment.getMaterialType(),
                appointment.getLicensePlateNumberOfTruck(),
                appointment.getTruckStatus(),
                appointment.getWarehouseNumber(),
                day,
                appointment.getInitialWeight(),
                appointment.getFinalWeight(),
                appointment.getArrivalTime(),
                appointment.getDepartureTime()
        );
    }
}

