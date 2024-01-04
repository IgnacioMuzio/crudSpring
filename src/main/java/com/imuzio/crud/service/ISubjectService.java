package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.Subject;

import java.util.List;

public interface ISubjectService {

    public List<Subject> findAll();

    public void save (Subject subject);

    public Subject findOne (Integer id);

    public void delete (Integer id);
}
