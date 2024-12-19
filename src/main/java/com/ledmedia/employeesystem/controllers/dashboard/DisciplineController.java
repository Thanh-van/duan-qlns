package com.ledmedia.employeesystem.controllers.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.services.DisciplineService;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard/disciplines")
public class DisciplineController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("disciplines", disciplineService.findAll());
            return "dashboard/disciplines/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/new")
    public String add(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("staffs", staffService.findAll());
            return "dashboard/disciplines/add";
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
    //             Discipline discipline = new Discipline();
    //             discipline.setStaff(staffSelected);
    //             discipline.setTitle(title);
    //             discipline.setContent(content);
    //             discipline.setTime(new Date());
    //             disciplineService.save(discipline);
    //             return "redirect:/dashboard/disciplines";
    //         } else {
    //             model.addAttribute("error", "Staff is not invalid, please choose in list suggest!!!");
    //             return "dashboard/disciplines/add";
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
                    disciplineService.deleteById(id);
                    return "redirect:/dashboard/disciplines/";
                default:
                    return "redirect:/dashboard";
            }
        } else {
            return "redirect:/auth/login";
        }
    }
}