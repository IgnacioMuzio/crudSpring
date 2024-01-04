package com.imuzio.crud.service;

import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService{

    @Autowired
    private SubjectRepository repository;

    @Override
    public List<Subject> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Subject subject) {
        repository.save(subject);
    }

    @Override
    public Subject findOne(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
