package com.vsii.repository;

import com.vsii.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByStudentId(long studentId);
    List<Student> findByNameAndCountry(String name, String country);
}
