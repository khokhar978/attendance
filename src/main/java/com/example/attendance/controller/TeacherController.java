package com.example.attendance.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.attendance.model.Student;
import com.example.attendance.model.Attendance;
import com.example.attendance.service.StudentService;
import com.example.attendance.service.AttendanceService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/students")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "teacher/students";
    }

    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "teacher/add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute("student") Student newStudent) {
        studentService.addStudent(newStudent);
        return "redirect:/teacher/students";
    }

    @GetMapping("/attendance")
    public String viewAttendance(Model model) {
        model.addAttribute("attendanceRecords", attendanceService.getAllAttendance());
        return "teacher/attendance";
    }

    @PostMapping("/attendance")
    public String takeAttendance(@RequestParam("studentId") Long studentId, @RequestParam("status") boolean status) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Check if attendance record already exists for today
        Attendance existingAttendance = attendanceService.getAttendanceByDateAndStudentId(currentDate, studentId);
        if (existingAttendance != null) {
            // Update existing attendance record
            existingAttendance.setStatus(status);
            attendanceService.saveAttendance(existingAttendance);
        } else {
            // Create new attendance record
            Attendance newAttendance = new Attendance();
            newAttendance.setDate(currentDate);
            newAttendance.setStudentId(studentId);
            newAttendance.setStatus(status);
            attendanceService.saveAttendance(newAttendance);
        }

        return "redirect:/teacher/attendance";
    }

    // Other methods for teacher actions
}
