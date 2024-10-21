package be.kdg.prog6.controllers;

import be.kdg.prog6.messages.UnSecuredMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured")
public class UnSecuredController {

    @GetMapping
    public UnSecuredMessage getUnsecured(){
        return new UnSecuredMessage();
    }

}
