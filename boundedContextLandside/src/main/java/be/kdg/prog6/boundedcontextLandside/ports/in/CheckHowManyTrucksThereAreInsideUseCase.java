package be.kdg.prog6.boundedcontextLandside.ports.in;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CheckHowManyTrucksThereAreInsideUseCase {
    int checkHowManyTrucksThereAreInside(LocalDateTime localDateTime);
}
