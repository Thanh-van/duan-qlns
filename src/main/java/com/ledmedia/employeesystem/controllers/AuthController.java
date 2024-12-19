package com.ledmedia.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired private StaffService staffService;
    @GetMapping(value = "/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping(value = "/login")
    public String loginProcess(HttpSession session, Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        if(staffService.isExists(username, password)) {
            Staff userLoggedIn = staffService.findByUsername(username);
            session.setAttribute("userLoggedIn", userLoggedIn);
            if(userLoggedIn.getPosition().equals("ADMIN")) {
                return "redirect:/dashboard/";
            } else {
                return "redirect:/client/";
            }
        } else {
            model.addAttribute("error", "Username or password incorrect!");
            return "auth/login";
        }
    }

    @GetMapping(value = "/logout")
    public String logoutProcess(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
    
}
