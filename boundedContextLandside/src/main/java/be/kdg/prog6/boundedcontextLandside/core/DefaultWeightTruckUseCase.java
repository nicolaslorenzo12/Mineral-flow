package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.AppointmentJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.WeightingTime;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DefaultWeightTruckUseCase implements WeightTruckUseCase {
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultWeightTruckUseCase(LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    public void weightTruck(WeightTruckCommand weightTruckCommand) {
        DailyCalendar dailyCalendar = loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(LocalDate.now());
        Appointment appointment = findAppointment(weightTruckCommand, dailyCalendar);
        int randomWeight = generateRandomWeight(weightTruckCommand.weightingTime());
        appointment.proccessWeighting(weightTruckCommand.weightingTime(),randomWeight);
        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));
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

    private Appointment findAppointment(WeightTruckCommand weightTruckCommand, DailyCalendar dailyCalendar){
        return dailyCalendar.findAppointmentByAppointmentUUID(weightTruckCommand.uuid());
    }

}
