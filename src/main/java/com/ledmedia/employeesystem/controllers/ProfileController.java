package com.ledmedia.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.models.Profile;
import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.services.ProfileService;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            Staff currentStaff = (Staff) session.getAttribute("userLoggedIn");
            model.addAttribute("currentStaff", currentStaff);
            return "profile/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/update/{id}")
    public String onUpdate(HttpSession session, Model model, @PathVariable(value = "id") long id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("profile.email") String email,
            @RequestParam("profile.name") String name,
            @RequestParam("profile.gender") String gender,
            @RequestParam("profile.address") String address,
            @RequestParam("profile.tel") String tel,
            @RequestParam("profile.experience") String experience,
            @RequestParam("profile.academicLevel") String academic_level) {
        if (SessionUtil.hasSession(session)) {
            Staff currentStaff = (Staff) session.getAttribute("userLoggedIn");
            Profile profile = currentStaff.getProfile();
            try {
                profile.setEmail(email);
                profile.setName(name);
                profile.setGender(gender);
                profile.setAddress(address);
                profile.setTel(tel);
                profile.setExperience(experience);
                profile.setAcademicLevel(academic_level);
                profileService.save(profile);
                currentStaff.setUsername(username);
                currentStaff.setPassword(password);
                currentStaff.setProfile(profile);
                staffService.save(currentStaff);
                return "redirect:/profile/";
            } catch (Exception ex) {
                model.addAttribute("error", ex.getMessage());
                model.addAttribute("currentStaff", currentStaff);
                return "profile/index";
            }
        } else {
            return "redirect:/auth/login";
        }
    }
}
