package com.ledmedia.employeesystem.controllers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.models.Department;
import com.ledmedia.employeesystem.services.DepartmentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("departments", departmentService.findAll());
            return "dashboard/departments/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/new")
    public String add(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            return "dashboard/departments/add";
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/add")
    public String onInsert(HttpSession session, Model model,
            @RequestParam String name,
            @RequestParam String description) {
        if (SessionUtil.hasSession(session)) {
            Department department = new Department();
            department.setName(name);
            department.setDescription(description);
            departmentService.save(department);
            return "redirect:/dashboard/departments";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/{id}")
    public String editOrRemove(HttpSession session, Model model, @RequestParam(value = "type") String type,
            @PathVariable(value = "id") long id) {
        if (SessionUtil.hasSession(session)) {
            switch (type) {
                case "edit":
                    model.addAttribute("objectSelected", departmentService.findById(id));
                    return "dashboard/departments/edit";
                case "remove":
                    try {
                        departmentService.deleteById(id);
                        return "redirect:/dashboard/departments";
                    } catch (Exception ex) {
                        model.addAttribute("departments", departmentService.findAll());
                        model.addAttribute("error", ex.getMessage());
                        return "dashboard/departments/index";
                    }
                default:
                    return "redirect:/dashboard/departments";
            }
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/update/{id}")
    public String onUpdate(HttpSession session, Model model, @PathVariable(value = "id") long id,
            @RequestParam String name,
            @RequestParam String description) {
        if (SessionUtil.hasSession(session)) {
            try {
                Department department = departmentService.findById(id);
                department.setName(name);
                department.setDescription(description);
                departmentService.save(department);
                return "redirect:/dashboard/departments";
            } catch (Exception ex) {
                model.addAttribute("error", ex.getMessage());
                model.addAttribute("objectSelected", departmentService.findById(id));
                return "dashboard/departments/edit";
            }
        } else {
            return "redirect:/auth/login";
        }
    }
}
