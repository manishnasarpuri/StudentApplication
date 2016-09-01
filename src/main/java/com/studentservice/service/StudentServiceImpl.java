/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.studentservice.service;

import com.studentservice.dao.StudentDAO;
import com.studentservice.entity.Student;
import com.studentservice.response.StudentResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manish
 */
@Service
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    private StudentDAO studentDAO;
    
    @Override
    public StudentResponse findStudentById(Long Id) {
        Student student = studentDAO.findById(Id);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getStudentId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        return studentResponse;
    }
    
    @Override
    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> list = new ArrayList<>();
        List<Student> findAll = studentDAO.findAll();
        for (Student student : findAll) {
            StudentResponse response = new StudentResponse();
            response.setId(student.getStudentId());
            response.setFirstName(student.getFirstName());
            response.setLastName(student.getLastName());
            list.add(response);
        }
        return list;
    }

    @Override
    public void createStudent(String firstName, String lastName) {
        studentDAO.create(firstName, lastName);
    }

    @Override
    public void deleteStudent(Long id) {
        studentDAO.delete(id);
    }
    
}
