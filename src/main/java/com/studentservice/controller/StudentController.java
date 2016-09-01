/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.studentservice.controller;

import com.studentservice.response.StudentResponse;
import com.studentservice.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/v1/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @RequestMapping(value="/all",produces = APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<StudentResponse> students() {
        return studentService.getAllStudents();
    }
    
    @RequestMapping(value="/{id}",produces = APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public StudentResponse student(@PathVariable("id") Long id) {
        return studentService.findStudentById(id);
    }
    
    @RequestMapping(value="/create",produces = APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createStudent(@RequestBody StudentResponse student) {
        studentService.createStudent(student.getFirstName(), student.getLastName());
    }
    
    @RequestMapping(value="/delete/{id}",produces = APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }
    
}
