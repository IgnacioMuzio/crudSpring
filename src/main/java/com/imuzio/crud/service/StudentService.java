package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentRepository repository;


    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public Student findOne(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
