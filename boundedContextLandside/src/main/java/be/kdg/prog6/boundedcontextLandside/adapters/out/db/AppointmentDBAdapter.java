package be.kdg.prog6.boundedcontextLandside.adapters.out.db;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Component;
@Component
public class AppointmentDBAdapter implements LoadAndCreateAppointmentPort {

    private final AppointmentRepository appointmentRepository;

    public AppointmentDBAdapter(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment loadAppointmentByLicensePlateNumberOfTruck(String licensePlateNumberOfTruck) {
        final AppointmentJpaEntity appointmentJpaEntity = appointmentRepository.findAppointmentJpaEntityByLicensePlateNumberOfTruck(licensePlateNumberOfTruck).
         orElseThrow(() -> new RuntimeException("Appointment not found"));

        return buildAppointmentObject(appointmentJpaEntity);
    }

    @Override
    public void createAppointment(Appointment appointment) {

        appointmentRepository.save(new AppointmentJpaEntity(appointment.getAppointmentUUID().uuid(), appointment.getSellerUUID().uuid(), appointment.getGateNumber(),
                appointment.getAppointmentTime(), appointment.getMaterialType(), appointment.getLicensePlateNumberOfTruck(), appointment.getStatus(),
                appointment.getWarehouseNumber()));
    }

    private Appointment buildAppointmentObject(AppointmentJpaEntity appointmentJpaEntity){

        return new Appointment(new Appointment.AppointmentUUID(appointmentJpaEntity.getAppointmentUUID()), new Seller.CustomerUUID(appointmentJpaEntity.getSellerUuid()),
                appointmentJpaEntity.getGateNumber(), appointmentJpaEntity.getAppointmentTime(), appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getLicensePlateNumberOfTruck(), appointmentJpaEntity.getWarehouseNumber());
    }
}
