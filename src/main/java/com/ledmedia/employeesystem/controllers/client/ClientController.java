package com.ledmedia.employeesystem.controllers.client;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.models.OnLeave;
import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.models.TimeSheet;
import com.ledmedia.employeesystem.services.OnLeaveService;
import com.ledmedia.employeesystem.services.TimeSheetService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    private OnLeaveService onLeaveService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        try {
            Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
            List<TimeSheet> timeSheets = timeSheetService.findAll().stream()
                    .filter((timeSheet) -> timeSheet.getStaff().getId() == userLoggedIn.getId()).toList();
            model.addAttribute("timeSheets", timeSheets);
            LocalDate today = LocalDate.now();
            model.addAttribute("today", today);
            if (timeSheets.stream().anyMatch(timeSheet -> timeSheet.getDate().equals(today))) {
                model.addAttribute("hasAttendance", true);
            }
            return "client/index";
        } catch (Exception ex) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/on-leaves")
    public String onLeavesList(HttpSession session, Model model) {
        try {
            Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
            List<OnLeave> onLeaves = onLeaveService.findAll().stream()
                    .filter((onLeave) -> onLeave.getStaff().getId() == userLoggedIn.getId()).toList();
            model.addAttribute("onLeaves", onLeaves);
            return "client/on-leaves/index";

        } catch (Exception ex) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/attendance")
    public String onAttendance(HttpSession session, Model model) {
        Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
        List<TimeSheet> timeSheets = timeSheetService.findAll().stream()
                .filter((timeSheet) -> timeSheet.getStaff().getId() == userLoggedIn.getId()).toList();
        LocalDate today = LocalDate.now();
        System.out.println(today);
        if (!timeSheets.stream().anyMatch(timeSheet -> timeSheet.getDate().equals(today))) {
            TimeSheet timeSheet = new TimeSheet();
            //timeSheet.setDate(today);
            timeSheet.setStaff(userLoggedIn);
            timeSheetService.save(timeSheet);
            return "redirect:/client";
        } else {
            return "redirect:/client";
        }
    }

    @PostMapping(value = "/take-leave")
    public String onTakeLeave(HttpSession session, Model model,
            @RequestParam String title,
            @RequestParam String content) {
        try {
            Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
            Date today = new Date();
            System.out.println(today);
            OnLeave onLeave = new OnLeave();
            onLeave.setTitle(title);
            // onLeave.setContent(content);
            //onLeave.setTime(today);
            onLeave.setStatus("PENDING");
            onLeave.setStaff(userLoggedIn);
            onLeaveService.save(onLeave);
            return "redirect:/client";
        } catch (Exception ex) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/felicitations")
    public String felicitations(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
            model.addAttribute("felicitations", userLoggedIn.getFelicitations());
            return "client/felicitations/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/disciplines")
    public String disciplines(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            Staff userLoggedIn = (Staff) session.getAttribute("userLoggedIn");
            model.addAttribute("disciplines", userLoggedIn.getDisciplines());
            return "client/disciplines/index";
        } else {
            return "redirect:/auth/login";
        }
    }
}