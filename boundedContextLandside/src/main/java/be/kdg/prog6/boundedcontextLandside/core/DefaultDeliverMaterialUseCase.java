package be.kdg.prog6.boundedcontextLandside.core;

import be.kdg.prog6.boundedcontextLandside.domain.Appointment;
import be.kdg.prog6.boundedcontextLandside.domain.DailyCalendar;
import be.kdg.prog6.boundedcontextLandside.domain.TruckStatus;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialCommand;
import be.kdg.prog6.boundedcontextLandside.ports.in.DeliverMaterialUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.out.LoadOrCreateDailyCalendarPort;
import be.kdg.prog6.boundedcontextLandside.ports.out.UpdateDailyCalendarPort;
import be.kdg.prog6.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultDeliverMaterialUseCase implements DeliverMaterialUseCase {
    private final LoadOrCreateDailyCalendarPort loadDailyCalendarPort;
    private final List<UpdateDailyCalendarPort> updateDailyCalendarPorts;

    public DefaultDeliverMaterialUseCase(LoadOrCreateDailyCalendarPort loadDailyCalendarPort, List<UpdateDailyCalendarPort> updateDailyCalendarPorts) {
        this.loadDailyCalendarPort = loadDailyCalendarPort;
        this.updateDailyCalendarPorts = updateDailyCalendarPorts;
    }
    @Override
    public void deliverMaterial(DeliverMaterialCommand loadMaterialCommand) {

        Appointment appointment = findAppointmentByUUID(loadMaterialCommand.appointmentUUID());
        appointment.checkIfTruckHasAlreadyGottenThisStatus(TruckStatus.RECEIVE_MATERIAL);
        updateDailyCalendarPorts.forEach(updateDailyCalendarPort -> updateDailyCalendarPort.updateAppointment(appointment,new DailyCalendar(LocalDate.now())));
    }

    private Appointment findAppointmentByUUID(Appointment.AppointmentUUID appointmentUUID){

        return loadDailyCalendarPort.loadAppointmentByAppointmentUUID(appointmentUUID, new DailyCalendar(LocalDate.now()))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Appointment was not found"));
    }
}
