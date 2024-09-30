package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultScanLicensePlateNumberWhenArrivingUseCase implements ScanLicensePlateNumberWhenArrivingUseCase {

    private final LoadAndCreateAppointmentPort loadAndCreateAppointmentPort;
    private final UpdateAppointmentPort updateAppointmentPort;

    public DefaultScanLicensePlateNumberWhenArrivingUseCase(LoadAndCreateAppointmentPort loadAndCreateAppointmentPort, UpdateAppointmentPort updateAppointmentPort) {
        this.loadAndCreateAppointmentPort = loadAndCreateAppointmentPort;
        this.updateAppointmentPort = updateAppointmentPort;
    }

    @Override
    public void scanLicensePlateNumber(ScanLicensePlateNumberWhenArrivingCommand scanLicensePlateNumberCommand) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime roundedTime = now.withMinute(0).withSecond(0).withNano(0);

        Appointment appointment = loadAndCreateAppointmentPort.loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay
                        (scanLicensePlateNumberCommand.licensePlateNumber(), roundedTime, roundedTime.toLocalDate())
                .orElseThrow(() ->
                        new CustomException(HttpStatus.NOT_FOUND, "This truck does not have an appointment today at this time"));

        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.ARRIVED.getCode());
        updateAppointmentPort.updateAppointmentArrivalOrDepartureTime(appointment.getAppointmentUUID(), TruckStatus.ARRIVED);
        updateAppointmentPort.updateAppointmentTruckStatus(appointment.getAppointmentUUID(), TruckStatus.ARRIVED);
    }
}
