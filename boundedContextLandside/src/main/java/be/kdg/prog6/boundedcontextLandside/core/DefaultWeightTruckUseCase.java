package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultWeightTruckUseCase implements WeightTruckUseCase {

    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final UpdateAppointmentPort updateAppointmentPort;

    public DefaultWeightTruckUseCase(LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, UpdateAppointmentPort updateAppointmentPort) {
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.updateAppointmentPort = updateAppointmentPort;
    }

    @Override
    public void weightTruck(WeightTruckCommand weightTruckCommand) {

        Appointment appointment = loadAndCreateAppointmentPort
                .loadAppointmentJpaEntityByAppointmentUUID(weightTruckCommand.uuid())
                .orElseThrow(() -> new ObjectNotFoundException("The appointment was not found"));

        int randomWeight = generateRandomWeight();

        processWeighing(appointment, weightTruckCommand.weighingCount(), randomWeight);
        updateAppointmentStatus(appointment, weightTruckCommand.weighingCount());
    }

    private int generateRandomWeight() {
        Random random = new Random();
        return random.nextInt(21) + 10;
    }

    private void processWeighing(Appointment appointment, int weighingCount, int weight) {
        if (weighingCount == 1) {
            appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGFIRSTTIME.getCode());
        }
        else {
            appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.WEIGHTINGLASTTIME.getCode());
        }

        updateAppointmentPort.updateAppointmentInitialOrFinalWeight(appointment.getAppointmentUUID(), weight, weighingCount);
    }

    private void updateAppointmentStatus(Appointment appointment, int weighingCount) {
        if (weighingCount == 1) {
            updateAppointmentPort.updateAppointmentTruckStatus(appointment.getAppointmentUUID(), TruckStatus.WEIGHTINGFIRSTTIME);
        } else {
            updateAppointmentPort.updateAppointmentTruckStatus(appointment.getAppointmentUUID(), TruckStatus.WEIGHTINGLASTTIME);
        }
    }
}
