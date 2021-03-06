/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Manish
 */
@Entity
@Table(name = "STUDENT")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "STUDENT_SEQ",allocationSize = 1)
    @Column(name = "ID")
    private long studentId;
    
    @Column(name = "FIRSTNAME",nullable = false)
    private String firstName;
    
    @Column(name = "LASTNAME",nullable = false)
    private String lastName;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
