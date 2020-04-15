package com.vsii.service;

import com.vsii.model.Student;
import com.vsii.model.StudentView;
import com.vsii.repository.StudentViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentViewService implements IStudentViewService{

    @Autowired
    private StudentViewRepository studentViewRepository;

    @Override
    public StudentView getStudentViewById(long studentViewId) {
        StudentView obj = studentViewRepository.findByStudentViewId(studentViewId);
        return obj;
    }

    @Override
    public boolean addStudentView(StudentView studentView) {
        List<StudentView> list = studentViewRepository.findByNameView(studentView.getNameView());
        if (list.size() > 0) {
            return false;
        } else {
            studentView = studentViewRepository.save(studentView);
            return true;
        }
    }
}
