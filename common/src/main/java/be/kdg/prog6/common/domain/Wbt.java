package be.kdg.prog6.common.domain;
import java.time.LocalDateTime;
import java.util.UUID;

public class Wbt {

    private int initialWeight;
    private int finalWeight;
    private LocalDateTime initialWeightTime;
    private LocalDateTime finalWeightTime;
    private wbtUUID wbtUUid;
    public record wbtUUID(UUID uuid){

    }
}
