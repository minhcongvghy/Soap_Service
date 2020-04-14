package com.vsii.service;

import com.vsii.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();
    Student getStudentById(long studentId);
    boolean addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
