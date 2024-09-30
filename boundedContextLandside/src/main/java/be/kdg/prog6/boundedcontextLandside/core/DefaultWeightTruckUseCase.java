package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateWarehousePort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class DefaultWeightTruckUseCase implements WeightTruckUseCase {

    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final UpdateAppointmentPort updateAppointmentPort;

    private final UpdateWarehousePort updateWarehousePort;

    public DefaultWeightTruckUseCase(LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, UpdateAppointmentPort updateAppointmentPort, UpdateWarehousePort updateWarehousePort) {
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.updateAppointmentPort = updateAppointmentPort;
        this.updateWarehousePort = updateWarehousePort;
    }

    @Override
    public void weightTruck(WeightTruckCommand weightTruckCommand) {

        Appointment appointment = loadAppointment(weightTruckCommand);

        int randomWeight = generateRandomWeight(weightTruckCommand.weighingCount());

        processWeighing(appointment, weightTruckCommand.weighingCount(), randomWeight);
        updateAppointmentStatus(appointment, weightTruckCommand.weighingCount());
        calculateNetWeightIfTruckWeightedForLastTime(weightTruckCommand);
    }

    private void calculateNetWeightIfTruckWeightedForLastTime(WeightTruckCommand weightTruckCommand){

        if(weightTruckCommand.weighingCount() == 2){
            //Reload so I can get the object with the final weight
            Appointment appointmentWithFinalWeight = loadAppointment(weightTruckCommand);
            updateWarehousePort.addAmountOfTonsToWarehouseInWarehouseContext(appointmentWithFinalWeight.getNetWeight(),
                    appointmentWithFinalWeight.getWarehouseNumber());
            updateAppointmentPort.updateAppointmentArrivalOrDepartureTime(weightTruckCommand.uuid(), TruckStatus.LEFT);
        }
    }

    private int generateRandomWeight(int weighingCount) {
        Random random = new Random();
        if(weighingCount == 1) {
            return random.nextInt(21) + 10;
        }
        else{
            return 10;
        }
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
            updateAppointmentPort.updateAppointmentTruckStatus(appointment.getAppointmentUUID(), TruckStatus.LEFT);
        }
    }

    private Appointment loadAppointment(WeightTruckCommand weightTruckCommand){
        return loadAndCreateAppointmentPort
                .loadAppointmentByAppointmentUUID(weightTruckCommand.uuid())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"The appointment was not found"));
    }
}
