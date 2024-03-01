package com.example.Server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HomeController {
    @GetMapping("/")
    private String getInfo(){
        return "Разработка комплекса программных приложений для агентства недвижимости";
    }

}
