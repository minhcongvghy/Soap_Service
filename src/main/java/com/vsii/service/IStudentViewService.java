package com.vsii.service;

import com.vsii.model.StudentView;

public interface IStudentViewService {

    StudentView getStudentViewById(long studentViewId);

    boolean addStudentView(StudentView studentView);
}
