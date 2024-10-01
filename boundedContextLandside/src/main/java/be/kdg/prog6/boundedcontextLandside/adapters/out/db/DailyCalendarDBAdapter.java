package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadDailyCalendarPort;
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
public class DailyCalendarDBAdapter implements LoadDailyCalendarPort, UpdateDailyCalendarPort {

    private final DailyCalendarRepository dailyCalendarRepository;

    public DailyCalendarDBAdapter(DailyCalendarRepository dailyCalendarRepository) {
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    private List<Appointment>
    buildAppointmentObjects(List<AppointmentJpaEntity> appointmentJpaEntityList) {
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

    @Override
    public void createAppointment(Appointment appointment, DailyCalendar dailyCalendar) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(dailyCalendar.getDay()).
                orElseGet(() -> createNewDailyCalendar(dailyCalendar.getDay()));

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getTruckStatus(),
                appointment.getWarehouseNumber(), appointment.getAppointmentTime().toLocalDate(), appointment.getInitialWeight(), appointment.getFinalWeight());

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day) {

        DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(day).
                orElseGet(() -> createNewDailyCalendar(day));

        List<AppointmentJpaEntity> appointmentJpaEntityList = dailyCalendarJpaEntity.getAppointments();

        return appointmentJpaEntityList.stream()
                .filter(appointment -> appointment.getLicensePlateNumberOfTruck().equals(licensePlateNumberOfTruck)
                        && appointment.getAppointmentTime().equals(localDateTime)
                        && appointment.getDay().equals(day))
                .findFirst()
                .map(this::buildAppointmentObject);

    }

    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity) {
        return new Appointment(
                new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()),
                new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
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
        );
    }


    private DailyCalendar buildDailyCalendarObject(final DailyCalendarJpaEntity dailyCalendarJpaEntity){

        LocalDate day = dailyCalendarJpaEntity.getDay();
        List<AppointmentJpaEntity> appointmentJpaEntityList  = dailyCalendarJpaEntity.getAppointments();
        List<Appointment> appointments =  buildAppointmentObjects(appointmentJpaEntityList);
        return new DailyCalendar(day, appointments);
    }

    @Override
    public Optional<Appointment> loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID, DailyCalendar dailyCalendar) {

        DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(dailyCalendar.getDay()).
                orElseGet(() -> createNewDailyCalendar(dailyCalendar.getDay()));

        AppointmentJpaEntity appointmentJpaEntity = dailyCalendarJpaEntity.getAppointments().stream()
                .filter(appt -> appt.getAppointmentUUID().equals(appointmentUUID.uuid()))
                .findFirst().get();

        return Optional.of(buildAppointmentObject(appointmentJpaEntity));
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

    @Override
    public void updateAppointment(Appointment appointment, DailyCalendar dailyCalendar) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(dailyCalendar.getDay()).
                orElseGet(() -> createNewDailyCalendar(dailyCalendar.getDay()));

        AppointmentJpaEntity appointmentJpaEntity = dailyCalendarJpaEntity.getAppointments().stream()
                .filter(appt -> appt.getAppointmentUUID().equals(appointment.getAppointmentUUID().uuid()))
                .findFirst().get();

        appointmentJpaEntity.setSellerUuid(appointment.getSellerUUID().uuid());
        appointmentJpaEntity.setGateNumber(appointment.getGateNumber());
        appointmentJpaEntity.setAppointmentTime(appointment.getAppointmentTime());
        appointmentJpaEntity.setMaterialType(appointment.getMaterialType());
        appointmentJpaEntity.setLicensePlateNumberOfTruck(appointment.getLicensePlateNumberOfTruck());
        appointmentJpaEntity.setStatus(appointment.getTruckStatus());
        appointmentJpaEntity.setWarehouseNumber(appointment.getWarehouseNumber());
        appointmentJpaEntity.setInitialWeight(appointment.getInitialWeight());
        appointmentJpaEntity.setFinalWeight(appointment.getFinalWeight());
        appointmentJpaEntity.setArrivalTime(appointment.getArrivalTime());
        appointmentJpaEntity.setDepartureTime(appointment.getDepartureTime());

        dailyCalendarRepository.save(dailyCalendarJpaEntity);

    }
}

