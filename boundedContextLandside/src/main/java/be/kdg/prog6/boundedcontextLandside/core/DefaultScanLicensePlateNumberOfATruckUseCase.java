package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberOfATruckCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberOfATruckUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadAndCreateAppointmentPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateAppointmentPort;
import be.kdg.prog6.common.exception.ThisTruckStatusWasAlreadyCheckedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime roundedTime = now.withMinute(0).withSecond(0).withNano(0);

        Appointment appointment = loadAndCreateAppointmentPort.loadAppointmentByLicensePlateNumberOfTruckAndAppointmentTimeAndDay
                        (scanLicensePlateNumberCommand.licensePlateNumber(), roundedTime, roundedTime.toLocalDate())
                .orElseThrow(() -> new RuntimeException("This truck does not have an appointment today at this time"));

        checkIfTruckHasAlreadyGottenThisStatus(appointment, TruckStatus.ARRIVED.getCode());

        updateAppointmentPort.updateAppointmentTruckStatus(appointment, TruckStatus.ARRIVED);
    }

    private void checkIfTruckHasAlreadyGottenThisStatus(Appointment appointment, int statusCode){

        if(appointment.getTruckStatus().getCode() >= statusCode){

            switch (statusCode){
                case 2 -> throw new ThisTruckStatusWasAlreadyCheckedException("This truck has already arrived to its appointment in this hour");
                case 3 -> throw new ThisTruckStatusWasAlreadyCheckedException("This truck was already weighted for first time");
            }
        }
    }

}
