package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.MakeAppointmentUseCase;
import be.kdg.prog6.common.domain.Material;
import be.kdg.prog6.common.domain.Seller;
import org.springframework.stereotype.Service;

@Service
public class DefaultMakeAppointmentUseCase implements MakeAppointmentUseCase {
    @Override
    public void makeAppointment(MakeAppointmentCommand makeAppointmentCommand) {

        final Seller.CustomerUUID sellerUUID = makeAppointmentCommand.sellerUUID();
        final Material.MaterialUUID materialUUID = makeAppointmentCommand.materialUUID();
    }
}
