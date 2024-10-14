package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.CheckHowManyTrucksThereAreInsideUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LandsideInfoController {

    private final CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase;

    public LandsideInfoController(CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase) {
        this.checkHowManyTrucksThereAreInsideUseCase = checkHowManyTrucksThereAreInsideUseCase;
    }

    @GetMapping("trucks-inside/{time}")
    public ResponseEntity<List<TruckDto>> howManyTrucksThereAreInside(@PathVariable LocalDateTime time){

        List<TruckDto> truckDtoList= checkHowManyTrucksThereAreInsideUseCase.checkHowManyTrucksThereAreInside(time);
        return ResponseEntity.ok(truckDtoList);
    }

}
