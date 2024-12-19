package com.ledmedia.employeesystem.controllers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.services.FelicitationService;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard/felicitations")
public class FelicitationController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private FelicitationService felicitationService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("felicitations", felicitationService.findAll());
            return "dashboard/felicitations/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/new")
    public String add(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("staffs", staffService.findAll());
            return "dashboard/felicitations/add";
        } else {
            return "redirect:/auth/login";
        }
    }

    // @PostMapping(value = "/add")
    // public String onInsert(HttpSession session, Model model,
    //         @RequestParam String staff_id,
    //         @RequestParam String title,
    //         @RequestParam String content) {
    //     if (SessionUtil.hasSession(session)) {
    //         Staff staffSelected = staffService.findById(Long.parseLong(staff_id));
    //         if (staffSelected != null) {
    //             Felicitation felicitation = new Felicitation();
    //             felicitation.setStaff(staffSelected);
    //             felicitation.setTitle(title);
    //             felicitation.setContent(content);
    //             felicitation.setTime(new Date());
    //             felicitationService.save(felicitation);
    //             return "redirect:/dashboard/felicitations";
    //         } else {
    //             model.addAttribute("error", "Staff is not invalid, please choose in list suggest!!!");
    //             return "dashboard/felicitations/add";
    //         }
    //     } else {
    //         return "redirect:/auth/login";
    //     }
    // }

    @GetMapping(value = "/{id}")
    public String editOrRemove(HttpSession session, Model model, @RequestParam(value = "type") String type,
            @PathVariable(value = "id") long id) {
        if (SessionUtil.hasSession(session)) {
            switch (type) {
                case "remove":
                    felicitationService.deleteById(id);
                    return "redirect:/dashboard/felicitations/";
                default:
                    return "redirect:/dashboard";
            }
        } else {
            return "redirect:/auth/login";
        }
    }
}
