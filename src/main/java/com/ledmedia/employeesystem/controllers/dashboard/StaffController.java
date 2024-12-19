package com.ledmedia.employeesystem.controllers.dashboard;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ledmedia.employeesystem.controllers.SessionUtil;
import com.ledmedia.employeesystem.models.Profile;
import com.ledmedia.employeesystem.models.Staff;
import com.ledmedia.employeesystem.services.DepartmentService;
import com.ledmedia.employeesystem.services.ProfileService;
import com.ledmedia.employeesystem.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dashboard/staffs")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = { "", "/" })
    public String index(HttpSession session, Model model,
            @RequestParam(value = "name", required = false) Optional<String> name) {
        if (SessionUtil.hasSession(session)) {
            List<Staff> staffList = staffService.findAll();
               if (staffList != null) {
                staffList = staffList.stream()
        .filter(staff -> staff != null && staff.getProfile() != null)
        .toList();
}

            model.addAttribute("staffList", staffList);
            return "dashboard/staffs/index";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping(value = "/add")
    public String add(HttpSession session, Model model) {
        if (SessionUtil.hasSession(session)) {
            model.addAttribute("departments", departmentService.findAll());
            return "dashboard/staffs/add";
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/add")
    public String onInsert(HttpSession session, Model model,
            @RequestParam long department_id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String gender,
            @RequestParam String address,
            @RequestParam String tel,
            @RequestParam String position,
            @RequestParam String experience,
            @RequestParam String academic_level,
            @RequestParam(required = true) long salary,
            @RequestParam String CCCD,
            @RequestParam String BHXH) {
        if (SessionUtil.hasSession(session)) {
            try {
                Staff staff = new Staff();
                staff.setUsername(username);
                staff.setPassword(password);
                staff.setPosition(position);
                staff.setDepartment(departmentService.findById(department_id));
                staff.setBaseSalary(salary);
                staff = staffService.save(staff);
                Profile profile = new Profile();
                profile.setName(name);
                profile.setEmail(email);
                profile.setAddress(address);
                profile.setTel(tel);
                profile.setGender(gender);
                profile.setStaff(staff);
                profile.setExperience(experience);
                profile.setAcademicLevel(academic_level);
                profile.setCCCD(CCCD);
                profile.setBHXH_number(BHXH);
                profile.setJoin_date(null);
                profileService.save(profile);
                return "redirect:/dashboard/staffs";
            } catch (Exception ex) {
                model.addAttribute("error", "Add fail!");
                return "dashboard/staffs/add";
            }
        } else {
            return "redirect:/auth/login";
        }

    }

    @GetMapping(value = "/{id}/")
    public String editOrRemove(HttpSession session, Model model,
     @PathVariable(value = "id") long id,
            @RequestParam(value = "type", defaultValue = "edit") String type) {
        if (SessionUtil.hasSession(session)) {
            Staff object = staffService.findById((id));
            switch (type) {
                case "edit":
                    model.addAttribute("objectSelected", object);
                    model.addAttribute("departments", departmentService.findAll());
                    return "dashboard/staffs/edit";
                case "remove":
                    profileService.delete(object.getProfile());
                    staffService.deleteById(id);
                    // model.addAttribute("objectSelected", model);
                    return "redirect:/dashboard/staffs";
                default:
                    return "redirect:/dashboard/staffs";

            }
        } else {
            return "redirect:/auth/login";

        }

    }

    /**
     * @param session
     * @param model
     * @param id
     * @param department_id
     * @param username
     * @param password
     * @param email
     * @param name
     * @param gender
     * @param address
     * @param tel
     * @param position
     * @param experience
     * @param academic_level
     * @return
     */
    @PostMapping(value = "/update/{id}")
    public String onUpdate(HttpSession session, Model model,
            @PathVariable("id") long id,
            @RequestParam("department.id") long department_id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("profile.email") String email,
            @RequestParam("profile.name") String name,
            @RequestParam("profile.gender") String gender,
            @RequestParam("profile.address") String address,
            @RequestParam("profile.tel") String tel,
            @RequestParam String position,
            @RequestParam("profile.experience") String experience,
            @RequestParam("profile.academicLevel") String academic_level,
            @RequestParam("profile.CCCD") String CCCD,
            @RequestParam("profile.BHXH") String BHXH,
            @RequestParam("profile.join_date") String joinDate,  // Điều chỉnh ở đây
            @RequestParam long salary) {

        if (SessionUtil.hasSession(session)) {
            Staff staff = staffService.findById(id);
            staff.setUsername(username);
            staff.setPassword(password);
            staff.setPosition(position);
            staff.setDepartment(departmentService.findById(department_id));
            staff.setSalary(salary);
            staff = staffService.save(staff);
            Profile profile = staff.getProfile();
            profile.setName(name);
            profile.setEmail(email);
            profile.setAddress(address);
            profile.setTel(tel);
            profile.setGender(gender);
            profile.setExperience(experience);
            profile.setAcademicLevel(academic_level);
            profile.setStaff(staff);
            profile.setBHXH_number(BHXH);
            profile.setJoin_date(Date.valueOf(joinDate));
            profile.setCCCD(CCCD);
            profileService.save(profile);
            return "redirect:/dashboard/staffs";
        } else {
            return "redirect:/auth/login";
        }
    }
}