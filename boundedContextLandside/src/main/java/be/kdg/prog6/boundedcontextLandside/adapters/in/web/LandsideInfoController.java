package be.kdg.prog6.boundedcontextLandside.adapters.in.web;

import be.kdg.prog6.boundedcontextLandside.domain.dto.TruckArrivedDto;
import be.kdg.prog6.boundedcontextLandside.ports.in.CheckHowManyTrucksThereAreInsideUseCase;
import be.kdg.prog6.boundedcontextLandside.ports.in.ScanLicensePlateNumberWhenArrivingCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class LandsideInfoController {

    private final CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase;

    public LandsideInfoController(CheckHowManyTrucksThereAreInsideUseCase checkHowManyTrucksThereAreInsideUseCase) {
        this.checkHowManyTrucksThereAreInsideUseCase = checkHowManyTrucksThereAreInsideUseCase;
    }

    @GetMapping("trucks-inside/{time}")
    public ResponseEntity<String> howManyTrucksThereAreInside(@PathVariable LocalDateTime time){

        int amountOfTrucksInside = checkHowManyTrucksThereAreInsideUseCase.checkHowManyTrucksThereAreInside(time);
        return ResponseEntity.ok("There are " + amountOfTrucksInside + " trucks inside");
    }

}
