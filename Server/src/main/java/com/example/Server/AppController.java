package com.example.Server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    private String getInfo(){
        return "Разработка комплекса программных приложений для агентства недвижимости";
    }
}
