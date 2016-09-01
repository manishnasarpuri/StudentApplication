/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentservice.dao;

import com.studentservice.entity.Student;
import java.util.List;

/**
 *
 * @author Manish
 */
public interface StudentDAO {
    Student findById(Long id);
    
    List<Student> findAll();
    
    void create(String firstName,String lastName);
    
    void delete(Long id);
}
