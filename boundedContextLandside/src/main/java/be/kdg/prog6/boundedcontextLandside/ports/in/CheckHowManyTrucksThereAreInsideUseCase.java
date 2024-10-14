package be.kdg.prog6.boundedcontextLandside.ports.in;

import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckHowManyTrucksThereAreInsideUseCase {
    List<TruckDto> checkHowManyTrucksThereAreInside(LocalDateTime localDateTime);
}
