package be.kdg.prog6.common.exception;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(final String message){
        super(message);
    }
}
