package com.ledmedia.employeesystem.controllers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.services.PayWaveLogService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard/pay-wage-logs")
public class PayWageLogController {
    @Autowired
    private PayWaveLogService payWaveLogService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("payWageLogs", payWaveLogService.findAll());
            return "dashboard/pay-wage-logs/index";
        } else {
            return "redirect:/auth/login";
        }
    }

}
