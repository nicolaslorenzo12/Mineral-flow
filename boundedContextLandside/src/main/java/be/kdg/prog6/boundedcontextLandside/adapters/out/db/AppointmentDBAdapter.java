package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.domain.Customer;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppointmentDBAdapter implements LoadAndCreateAppointmentPort, UpdateAppointmentPort {

    private final AppointmentRepository appointmentRepository;
    private final DailyCalendarRepository dailyCalendarRepository;

    public AppointmentDBAdapter(AppointmentRepository appointmentRepository, DailyCalendarRepository dailyCalendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dailyCalendarRepository = dailyCalendarRepository;
    }

    @Override
    public Optional<Appointment> loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(
            String licensePlateNumberOfTruck, LocalDateTime localDateTime, LocalDate day) {

        return appointmentRepository
                .findAppointmentJpaEntityByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(licensePlateNumberOfTruck, localDateTime, day)
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
                appointmentJpaEntity.getFinalWeight()
        );
    }

    @Override
    public void createAppointment(Appointment appointment, DailyCalendarJpaEntity dailyCalendarJpaEntity) {

        final AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(),appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getTruckStatus(),
                appointment.getWarehouseNumber(), appointment.getAppointmentTime().toLocalDate(), appointment.getInitialWeight(), appointment.getFinalWeight());

        dailyCalendarJpaEntity.addAppointment(appointmentJpaEntity);
        dailyCalendarRepository.save(dailyCalendarJpaEntity);
    }

    @Override
    public Optional<Appointment> loadAppointmentByAppointmentUUID(Appointment.AppointmentUUID appointmentUUID) {

        return appointmentRepository
                .findAppointmentJpaEntityByAppointmentUUID(appointmentUUID.uuid())
                .map(this::buildAppointmentObject);
    }

    @Override
    public void updateAppointmentTruckStatus(Appointment.AppointmentUUID appointmentUUID, TruckStatus truckStatus) {

        appointmentRepository.findAppointmentJpaEntityByAppointmentUUID(appointmentUUID.uuid()).ifPresent(appointmentJpaEntity -> {
            appointmentJpaEntity.setStatus(truckStatus);
            appointmentRepository.save(appointmentJpaEntity);
        });
    }

    @Override
    public void updateAppointmentInitialOrFinalWeight(Appointment.AppointmentUUID appointmentUUID, int weight, int weighingCount) {

        appointmentRepository.findAppointmentJpaEntityByAppointmentUUID(appointmentUUID.uuid()).ifPresent(appointmentJpaEntity -> {
            updateWeight(appointmentJpaEntity, weight, weighingCount);
            appointmentRepository.save(appointmentJpaEntity);
        });
    }

    private void updateWeight(AppointmentJpaEntity appointmentJpaEntity, int weight, int weighingCount) {
        if (weighingCount == 1) {
            appointmentJpaEntity.setInitialWeight(weight);
        } else {
            appointmentJpaEntity.setFinalWeight(weight);
        }
    }
}
