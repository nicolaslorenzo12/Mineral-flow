package be.kdg.prog6.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WarehouseCapacityExceededException.class)
    public ResponseEntity<String> handleWarehouseCapacityExceeded(WarehouseCapacityExceededException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());  // HTTP 409 Conflict
    }

    @ExceptionHandler(AppointmentsPerHourReachedException.class)
    public ResponseEntity<String> handleAppointmentsPerHourReached(AppointmentsPerHourReachedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());  // HTTP 409 Conflict
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStock(InsufficientStockException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleObjectNotFound(ObjectNotFoundException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }

}
