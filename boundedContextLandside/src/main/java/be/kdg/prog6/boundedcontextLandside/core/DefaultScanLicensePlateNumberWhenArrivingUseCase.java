package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.adapters.out.db.AppointmentJpaEntity;
import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckArrivedDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultScanLicensePlateNumberWhenArrivingUseCase implements ScanLicensePlateNumberWhenArrivingUseCase {
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultScanLicensePlateNumberWhenArrivingUseCase(LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }

    @Override
    public TruckArrivedDto scanLicensePlateNumber(ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime roundedTime = now.withMinute(0).withSecond(0).withNano(0);

        DailyCalendar dailyCalendar = loadDailyCalendarPort.loadOrCreateDailyCalendarByDay(roundedTime.toLocalDate());
        Appointment appointment = dailyCalendar.setArrivalTimeOfAnAppointment(scanLicensePlateNumberCommand.licensePlateNumber(),
                roundedTime);

        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateDailyCalendar(dailyCalendar, appointment));

        return new TruckArrivedDto(appointment.getAppointmentUUID(), appointment.getLicensePlateNumberOfTruck(), appointment.getTruckStatus(), appointment.getArrivalTime());
    }
}

