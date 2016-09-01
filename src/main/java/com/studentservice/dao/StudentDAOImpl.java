/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.studentservice.dao;

import com.studentservice.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Manish
 */
@Repository
public class StudentDAOImpl implements StudentDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }
    
    @Override
    public List<Student> findAll() {
        return (List<Student>)entityManager.createQuery("select s from Student s ").getResultList();
    }
    
    @Override
    @Transactional
    public void create(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        entityManager.persist(student);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }
    
}
