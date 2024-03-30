package com.example.attendance.service;

import com.example.attendance.model.Admin;
import com.example.attendance.model.Teacher;
import com.example.attendance.repository.AdminRepository;
import com.example.attendance.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, TeacherRepository teacherRepository) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
    }

    // Admin related operations

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Teacher related operations

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }
}
