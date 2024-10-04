package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
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

    @Override
    public void createAppointment(Appointment appointment, DailyCalendar dailyCalendar) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = dailyCalendarRepository.findDailyCalendarJpaEntityByDay(dailyCalendar.getDay()).
                orElseGet(() -> createNewDailyCalendar(dailyCalendar.getDay()));

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getTruckStatus(),
                appointment.getWarehouseNumber(), appointment.getAppointmentTime().toLocalDate(), appointment.getInitialWeight(), appointment.getFinalWeight(),
                appointment.getArrivalTime(), appointment.getDepartureTime());

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day) {

        DailyCalendarJpaEntity dailyCalendarJpaEntity = findDailyCalendarJpaEntity(day);

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
    public void updateAppointment(Appointment appointment, DailyCalendar dailyCalendar) {

        final DailyCalendarJpaEntity dailyCalendarJpaEntity = findDailyCalendarJpaEntity(dailyCalendar.getDay());

        Optional<AppointmentJpaEntity> appointmentJpaEntity = dailyCalendarJpaEntity.getAppointments().stream()
                .filter(appt -> appt.getAppointmentUUID().equals(appointment.getAppointmentUUID().uuid()))
                .findFirst();

        appointmentJpaEntity.ifPresent(appointmentJpaEntity2 -> {
            appointmentJpaEntity2.setSellerUuid(appointment.getSellerUUID().uuid());
            appointmentJpaEntity2.setGateNumber(appointment.getGateNumber());
            appointmentJpaEntity2.setAppointmentTime(appointment.getAppointmentTime());
            appointmentJpaEntity2.setMaterialType(appointment.getMaterialType());
            appointmentJpaEntity2.setLicensePlateNumberOfTruck(appointment.getLicensePlateNumberOfTruck());
            appointmentJpaEntity2.setStatus(appointment.getTruckStatus());
            appointmentJpaEntity2.setWarehouseNumber(appointment.getWarehouseNumber());
            appointmentJpaEntity2.setInitialWeight(appointment.getInitialWeight());
            appointmentJpaEntity2.setFinalWeight(appointment.getFinalWeight());
            appointmentJpaEntity2.setArrivalTime(appointment.getArrivalTime());
            appointmentJpaEntity2.setDepartureTime(appointment.getDepartureTime());

            dailyCalendarRepository.save(dailyCalendarJpaEntity);
        });

    }

    @Override
    public void updateDailyCalendar(DailyCalendar dailyCalendar) {
        final DailyCalendarJpaEntity dailyCalendarJpaEntity = findDailyCalendarJpaEntity(dailyCalendar.getDay());

        dailyCalendar.getAppointments().forEach(appointment -> {
            AppointmentJpaEntity appointmentJpaEntity = buildAppointmentJpaEntity(appointment, dailyCalendar.getDay());
            dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        });

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

