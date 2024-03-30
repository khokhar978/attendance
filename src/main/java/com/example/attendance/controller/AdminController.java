package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.attendance.model.Class;
import com.example.attendance.model.Teacher;
import com.example.attendance.model.Admin;
import com.example.attendance.service.ClassService;
import com.example.attendance.service.TeacherService;
import com.example.attendance.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClassService classService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/classes")
    public String viewClasses(Model model) {
        model.addAttribute("classes", classService.getAllClasses());
        return "admin/classes";
    }

    @GetMapping("/add-class")
    public String showAddClassForm(Model model) {
        model.addAttribute("class", new Class());
        return "admin/add-class";
    }

    @PostMapping("/add-class")
    public String addClass(@ModelAttribute("class") Class newClass) {
        classService.addClass(newClass);
        return "redirect:/admin/classes";
    }

    @GetMapping("/teachers")
    public String viewTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "admin/teachers";
    } 

    @GetMapping("/add-teacher")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "admin/add-teacher";
    }

    @PostMapping("/add-teacher")
    public String addTeacher(@ModelAttribute("teacher") Teacher newTeacher) {
        teacherService.addTeacher(newTeacher);
        return "redirect:/admin/teachers";
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "admin/manage-users";
    }

    @GetMapping("/add-admin")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/add-admin";
    }

    @PostMapping("/add-admin")
    public String addAdmin(@ModelAttribute("admin") Admin newAdmin) {
        adminService.addAdmin(newAdmin);
        return "redirect:/admin/manage-users";
    }
}
