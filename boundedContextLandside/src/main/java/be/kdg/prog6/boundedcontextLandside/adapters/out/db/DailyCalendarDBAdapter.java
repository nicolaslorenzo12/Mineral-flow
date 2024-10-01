package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadDailyCalendarPort;
import be.kdg.prog6.common.domain.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DailyCalendarDBAdapter implements LoadDailyCalendarPort {

    private final DailyCalendarRepository dailyCalendarRepository;

    public DailyCalendarDBAdapter(DailyCalendarRepository dailyCalendarRepository) {
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    private List<Appointment> toAppointments(List<AppointmentJpaEntity> appointmentJpaEntityList) {
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
                appointmentJpaEntity.getFinalWeight()
        )));
        return appointments;
    }

    private DailyCalendar buildDailyCalendarObject(final DailyCalendarJpaEntity dailyCalendarJpaEntity){

        LocalDate day = dailyCalendarJpaEntity.getDay();
        List<AppointmentJpaEntity> appointmentJpaEntityList  = dailyCalendarJpaEntity.getAppointments();
        List<Appointment> appointments =  toAppointments(appointmentJpaEntityList);
        return new DailyCalendar(day, appointments);
    }
    @Override
    public DailyCalendar loadOrCreateDailyCalendarByDay(LocalDate localDate) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(localDate).
                orElseGet(() -> createNewDailyCalendar(localDate));

        return buildDailyCalendarObject(dailyCalendarJpaEntity);
    }

    private DailyCalendarJpaEntity createNewDailyCalendar(LocalDate localDate){

        DailyCalendarJpaEntity dailyCalendarJpaEntity = new DailyCalendarJpaEntity(localDate);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
        return dailyCalendarJpaEntity;
    }
}

