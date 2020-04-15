package com.vsii.repository;

import com.vsii.model.Student;
import com.vsii.model.StudentView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentViewRepository extends CrudRepository<StudentView, Long> {

    StudentView findByStudentViewId(long studentViewId);

    List<StudentView> findByNameView(String nameView);
}
