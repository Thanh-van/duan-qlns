package com.ledmedia.employeesystem.controllers.dashboard;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.models.TimeSheet;
import com.ledmedia.employeesystem.services.StaffService;
import com.ledmedia.employeesystem.services.TimeSheetService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value = "/dashboard/time-sheets")
public class TimeSheetController {
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    private StaffService staffService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("staffs", staffService.findAll());
            return "dashboard/time-sheets/index";
        } else {
            return "redirect:/auth/login";
        }
    }
    @GetMapping(value = "/new")
    public String add(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("staffs",staffService.findAll());
                    return "dashboard/time-sheets/add";
        }else{
            return "redirect:/auth/login";
        }
    }
    @PostMapping(value = "/add")
    public String onInsert(HttpSession session, Model model,
    @RequestParam long staff_id,
    @RequestParam String status) {
    if(SessionUtil.hasSession(session)){
        Staff staffSelected = staffService.findById(staff_id);
    if(staffSelected != null){
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setStaff(staffSelected);
        timeSheet.setDate(new Date());
        timeSheet.setStatus(status);
        return "redirect:/dashboard/time-sheets";
    }else{
        model.addAttribute("error", "Staff is not valid, please choose in list sugget!!!");
        return "redirect:/dashboard/time-sheets/add";
    }
}
    return "redirect:auth/login";
    }
@GetMapping(value = "/{id}")
public String editOrRemove(HttpSession session, Model model,
@PathVariable(value = "id") long id,
@RequestParam(value = "type", defaultValue = "edit") String type){
    if (SessionUtil.hasSession(session)) {
        switch (type) {
            case "edit":
                model.addAttribute("objectSelected", timeSheetService.findById(id));
                return "redirect:/dashboard/time-sheets/edit";
            case "remove":
             try {
                timeSheetService.deleteById(id);
                return "redirect:/dashboard/time-sheets";
            } catch (Exception ex) {
                model.addAttribute("timeSheets", timeSheetService.findAll());
                model.addAttribute("error", ex.getMessage());
                return "dashboard/time-sheets/index";
            }
        default:
            return "redirect:/dashboard/time-sheets";
        }
    }else{
    return "redirect:auth/login";
    }
}
}

    


