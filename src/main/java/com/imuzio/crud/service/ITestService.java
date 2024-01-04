package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.entity.Test;

import java.util.List;

public interface ITestService {

    List<Test> findAll();

    void save(Test test);

    public List <Test> getTestsByStudentId(Integer id);

    List <Subject> getSubjectByTestStudentId(Integer id);

    List <Test> getTestsBySubjectId(Integer id);

    List <Student> getStudentByTestSubjectId(Integer id);
}
