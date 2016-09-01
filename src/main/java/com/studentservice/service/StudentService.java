/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentservice.service;

import com.studentservice.response.StudentResponse;
import java.util.List;

/**
 *
 * @author Manish
 */
public interface StudentService {
    
    StudentResponse findStudentById(Long Id);
    
    List<StudentResponse> getAllStudents();
    
    void createStudent(String firstName,String lastName);
    
    void deleteStudent(Long id);
}
