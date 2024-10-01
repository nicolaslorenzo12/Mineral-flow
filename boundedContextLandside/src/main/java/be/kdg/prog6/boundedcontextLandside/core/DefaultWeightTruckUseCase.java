package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.WeightingTime;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class DefaultWeightTruckUseCase implements WeightTruckUseCase {
    private final LoadDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultWeightTruckUseCase(LoadDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    public void weightTruck(WeightTruckCommand weightTruckCommand) {
        Appointment appointment = loadAppointment(weightTruckCommand);
        int randomWeight = generateRandomWeight(weightTruckCommand.weightingTime());
        Appointment finalAppointment = appointment.proccessWeighting(weightTruckCommand.weightingTime(),randomWeight);
        updateDailyCalendarPorts.forEach(updateAppointmentPort -> updateAppointmentPort.updateAppointment(finalAppointment, new DailyCalendar(LocalDate.now())));
    }

    private int generateRandomWeight(WeightingTime weightingTime) {
        Random random = new Random();
        if(weightingTime.equals(WeightingTime.FIRST_TIME)) {
            return random.nextInt(21) + 10;
        }
        else{
            return 10;
        }
    }

    private Appointment loadAppointment(WeightTruckCommand weightTruckCommand){

        return loadDailyCalendarPort.loadAppointmentByAppointmentUUID(new Appointment.AppointmentUUID(weightTruckCommand.uuid().uuid()), new DailyCalendar(LocalDate.now()))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"The appointment was not found"));
    }
}
