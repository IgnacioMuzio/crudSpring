package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.entity.Test;
import com.imuzio.crud.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService implements ITestService{

    @Autowired
    private TestRepository repository;

    @Override
    public List<Test> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Test test) {
        repository.save(test);
    }

    public List <Test> getTestsByStudentId(Integer id){
        return repository.getTestsByStudentId(id);
    }

    public List <Subject> getSubjectByTestStudentId(Integer id) {
        return repository.getSubjectByTestStudentId(id);
    }

    public List <Test> getTestsBySubjectId(Integer id){
        return repository.getTestsBySubjectId(id);
    }

    public List <Student> getStudentByTestSubjectId(Integer id){
        return repository.getStudentByTestSubjectId(id);
    }
}
