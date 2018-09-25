package com.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//extends off of @Component so will get pickup by component scan
@Controller
public class HomeController {

    @RequestMapping("/")
    public String showPage()
    {
        return "main-menu";
    }
}

