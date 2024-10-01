package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultDeliverMaterialUseCase implements DeliverMaterialUseCase {

    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final List<UpdateAppointmentPort> updateAppointmentPorts;

    public DefaultDeliverMaterialUseCase(LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, List<UpdateAppointmentPort> updateAppointmentPorts) {
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.updateAppointmentPorts = updateAppointmentPorts;
    }

    @Override
    public void deliverMaterial(DeliverMaterialCommand loadMaterialCommand) {

        Appointment appointment = loadAndCreateAppointmentPort.loadAppointmentByAppointmentUUID(loadMaterialCommand.appointmentUUID())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Appointment was not found"));

        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.RECEIVE_MATERIAL);
        updateAppointmentPorts.forEach(updateAppointmentPort -> updateAppointmentPort.updateAppointment(appointment, LocalDate.now()));
    }
}
