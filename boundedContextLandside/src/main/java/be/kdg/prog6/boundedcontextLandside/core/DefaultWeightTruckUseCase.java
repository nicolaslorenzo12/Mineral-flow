package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckWeightedDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.WeightTruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultWeightTruckUseCase implements WeightTruckUseCase {
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultWeightTruckUseCase(LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    public TruckWeightedDto weightTruck(WeightTruckCommand weightTruckCommand) {
        DailyCalendar dailyCalendar = loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(LocalDate.now());

        Appointment appointment = dailyCalendar.weightTruckOfAnAppointment(weightTruckCommand.uuid());

        updateDailyCalendarPorts.forEach(updateDailyCalendarPort ->
                updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));

        return new TruckWeightedDto(appointment.getAppointmentUUID(), appointment.getLicensePlateNumberOfTruck(),
                appointment.getInitialWeight(), appointment.getFinalWeight());
    }
}
