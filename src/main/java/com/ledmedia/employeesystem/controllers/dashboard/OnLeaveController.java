package com.ledmedia.employeesystem.controllers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.models.OnLeave;
import com.ledmedia.employeesystem.services.OnLeaveService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard/on-leaves")
public class OnLeaveController {
    @Autowired
    OnLeaveService onLeaveService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("onLeaves", onLeaveService.findAll());
            return "dashboard/on-leaves/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/{id}")
    public String editOrRemove(HttpSession session, Model model, @PathVariable(value = "id") long id,
            @RequestParam(value = "type", defaultValue = "approve") String type) {
        if (SessionUtil.hasSession(session)) {
            OnLeave onLeave = onLeaveService.findById(id);
            switch (type) {
                case "approve":
                    onLeave.setStatus("APPROVED");
                    onLeaveService.save(onLeave);
                    return "redirect:/dashboard/on-leaves";
                case "reject":
                    onLeave.setStatus("REJECTED");
                    onLeaveService.save(onLeave);
                    return "redirect:/dashboard/on-leaves";
                case "remove":
                    onLeaveService.deleteById(id);
                    return "redirect:/dashboard/on-leaves";
                default:
                    return "redirect:/dashboard/on-leaves";

            }
        } else {
            return "redirect:/auth/login";
        }
    }
}
