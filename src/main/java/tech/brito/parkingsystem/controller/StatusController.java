package tech.brito.parkingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping
    public String getStatus(){
        return "Bem vindo";
    }
}
