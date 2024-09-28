package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberOfATruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberOfATruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.exception.TruckArrivedWrongDateException;
import be.kdg.prog6.common.exception.TruckArrivingToAppointmentAtAnIncorrectTimeException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DefaultScanLicensePlateNumberOfATruckUseCase implements ScanLicensePlateNumberOfATruckUseCase {

    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final UpdateAppointmentPort updateAppointmentPort;

    public DefaultScanLicensePlateNumberOfATruckUseCase(LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, UpdateAppointmentPort updateAppointmentPort) {
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.updateAppointmentPort = updateAppointmentPort;
    }

    @Override
    public void scanLicensePlateNumber(ScanLicensePlateNumberOfATruckCommand scanLicensePlateNumberCommand) {

        Appointment appointment = loadAndCreateAppointmentPort.loadAppointmentByLicensePlateNumberOfTruck(scanLicensePlateNumberCommand.licensePlateNumber())
                .orElseThrow(() -> new RuntimeException("Appointment was not found"));

        checkIfTruckArrivalInCorrectTimeRange(appointment.getAppointmentTime());

        updateAppointmentPort.updateAppointmentTruckStatus(appointment, TruckStatus.ARRIVED);
    }

    public void checkIfTruckArrivalInCorrectTimeRange(LocalDateTime appointmentTime) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAfter = appointmentTime.plus(1, ChronoUnit.HOURS);

        if(!appointmentTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())){
            throw new TruckArrivedWrongDateException("Truck does not have an appointment today");
        }
        else if (now.isBefore(appointmentTime)) {
            throw new TruckArrivingToAppointmentAtAnIncorrectTimeException("Truck has arrived too early. It must wait until the scheduled time.");
        }
        else if (now.isAfter(oneHourAfter)) {
            throw new TruckArrivingToAppointmentAtAnIncorrectTimeException("Truck has arrived too late for the appointment.");
        }

    }
}
