package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void scanLicensePlateNumber(ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime roundedTime = now.withMinute(0).withSecond(0).withNano(0);

        Appointment appointment = findAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(scanLicensePlateNumberCommand.licensePlateNumber(),
                roundedTime, roundedTime.toLocalDate());

        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.ARRIVED);
        appointment.setArrivalTime(LocalDateTime.now());
        updateDailyCalendarPorts.forEach(updateAppointmentPort -> updateAppointmentPort.updateAppointment(appointment, new DailyCalendar(now.toLocalDate())));
    }

    public Appointment findAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay(String licensePlateNumber, LocalDateTime roundedTime, LocalDate day){
        return loadDailyCalendarPort.loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay
                        (licensePlateNumber, roundedTime, roundedTime.toLocalDate())
                .orElseThrow(() ->
                        new CustomException(HttpStatus.NOT_FOUND, "This truck does not have an appointment today at this time"));
    }
}
