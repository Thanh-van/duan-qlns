package com.ledmedia.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ledmedia.employeesystem.services.DepartmentService;
import com.ledmedia.employeesystem.services.DisciplineService;
import com.ledmedia.employeesystem.services.FelicitationService;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FelicitationService felicitationService;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            try {
                model.addAttribute("sumStaff", staffService.findAll().size());
                model.addAttribute("sumDepartment", departmentService.findAll().size());
                model.addAttribute("sumDiscipline", disciplineService.findAll().size());
                model.addAttribute("sumFelicitation", felicitationService.findAll().size());
                return "dashboard";
            } catch (Exception ex) {
                return "redirect:/auth/login";
            }
        } else {
            return "redirect:/auth/login";
        }
    }
}
