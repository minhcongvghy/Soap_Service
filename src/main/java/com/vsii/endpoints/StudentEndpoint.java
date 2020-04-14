package com.vsii.endpoints;

import com.vsii.gs_ws.*;
import com.vsii.model.Student;
import com.vsii.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://www.vsii.com/student-ws";
    @Autowired
    private IStudentService studentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudent(@RequestPayload GetStudentByIdRequest request) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(studentService.getStudentById(request.getStudentId()), studentInfo);
        response.setStudentInfo(studentInfo);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents() {
        GetAllStudentsResponse response = new GetAllStudentsResponse();
        List<StudentInfo> studentInfoList = new ArrayList<>();
        List<Student> studentList = studentService.getAllStudents();
        for (int i = 0; i < studentList.size(); i++) {
            StudentInfo ob = new StudentInfo();
            BeanUtils.copyProperties(studentList.get(i), ob);
            studentInfoList.add(ob);
        }
        response.getStudentInfo().addAll(studentInfoList);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addStudentRequest")
    @ResponsePayload
    public AddStudentResponse addStudent(@RequestPayload AddStudentRequest request) {
        AddStudentResponse response = new AddStudentResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Student student = new Student();
        student.setName(request.getName());
        student.setCountry(request.getCountry());
        boolean flag = studentService.addStudent(student);
        if (flag == false) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(student, studentInfo);
            response.setStudentInfo(studentInfo);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse updateStudent(@RequestPayload UpdateStudentRequest request) {
        Student student = new Student();
        BeanUtils.copyProperties(request.getStudentInfo(), student);
        studentService.updateStudent(student);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateStudentResponse response = new UpdateStudentResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse deleteStudent(@RequestPayload DeleteStudentRequest request) {
        Student student = studentService.getStudentById(request.getStudentId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (student == null ) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            studentService.deleteStudent(student);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
