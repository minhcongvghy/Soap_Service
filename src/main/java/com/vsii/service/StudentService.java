package com.vsii.service;

import com.vsii.model.Student;
import com.vsii.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        studentRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Student getStudentById(long studentId) {
        Student obj = studentRepository.findByStudentId(studentId);
        return obj;
    }

    @Override
    public boolean addStudent(Student student) {
        List<Student> list = studentRepository.findByNameAndCountry(student.getName(), student.getCountry());
        if (list.size() > 0) {
            return false;
        } else {
            student = studentRepository.save(student);
            return true;
        }
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
