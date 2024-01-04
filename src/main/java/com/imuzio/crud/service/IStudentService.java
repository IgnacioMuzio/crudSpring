package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> findAll();

    public void save (Student student);

    public Student findOne (Integer id);

    public void delete (Integer id);
}
