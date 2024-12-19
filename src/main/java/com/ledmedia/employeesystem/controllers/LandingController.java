package com.ledmedia.employeesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    @GetMapping(value = {"/", "/landing", ""})
    public String landing() {
        return "landing";
    }
}
