package com.vsii.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="students_view")
public class StudentView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="student_id")
    private long studentViewId;
    @Column(name="name")
    private String nameView;

    public long getStudentViewId() {
        return studentViewId;
    }

    public void setStudentViewId(long studentViewId) {
        this.studentViewId = studentViewId;
    }

    public String getNameView() {
        return nameView;
    }

    public void setNameView(String nameView) {
        this.nameView = nameView;
    }
}
