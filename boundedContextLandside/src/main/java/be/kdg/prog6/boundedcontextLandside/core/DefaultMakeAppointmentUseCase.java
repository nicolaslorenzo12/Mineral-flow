package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadMaterialPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadSellerPort;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Seller;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DefaultMakeAppointmentUseCase implements MakeAppointmentUseCase {

    private final LoadSellerPort loadSellerPort;
    private final LoadMaterialPort loadMaterialPort;

    public DefaultMakeAppointmentUseCase(LoadSellerPort loadSellerPort, LoadMaterialPort loadMaterialPort) {
        this.loadSellerPort = loadSellerPort;
        this.loadMaterialPort = loadMaterialPort;
    }

    @Override
    @Transactional
    public void makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        final UUID sellerUUID = makeAppointmentCommand.sellerUUID().uuid();
        final MaterialType materialType = makeAppointmentCommand.materialType();

        final Seller seller = loadSellerPort.loadSellerByUUID(sellerUUID).orElseThrow(() -> new RuntimeException("Seller not found"));
        final Material material = loadMaterialPort.loadMaterialByMaterialType(materialType).orElseThrow(() -> new RuntimeException("Material not found"));
        int gateNumber = (int)(Math.random() * 10) + 1;

        Appointment appointment = new Appointment(new Appointment.AppointmentUUID(UUID.randomUUID()),seller.getCustomerUUID(), gateNumber,
                makeAppointmentCommand.appointmentTime(), material.getMaterialType(), makeAppointmentCommand.licensePlateNumber(), TruckStatus.NOTARRIVED );

        System.out.println(appointment.toString());

    }
}
