package com.example.attendance.service;

import com.example.attendance.model.Class;
import com.example.attendance.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public void addClass(Class newClass) {
        classRepository.save(newClass);
    }
}
