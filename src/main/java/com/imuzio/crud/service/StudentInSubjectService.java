package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.StudentInSubject;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.repository.StudentInSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInSubjectService implements IStudentInSubjectService {

    @Autowired
    private StudentInSubjectRepository repository;

    @Override
    public void delete(StudentInSubject sis) {
        repository.delete(sis);
    }

    @Override
    public void save(StudentInSubject studentInSubject) {
        repository.save(studentInSubject);
    }

    public StudentInSubject getByStudentNSubject(Integer idStudent, Integer idSubject){
        return repository.getByStudentNSubject(idStudent, idSubject);
    }

    public List<Subject> getSubjectsBySiS(Integer idStudent){
        return repository.getSubjectsBySiS(idStudent);
    }

    public List<Student> getStudentsBySiS(Integer idSubject){
        return repository.getStudentsBySiS(idSubject);
    }

    public List<Subject> getNotSubjectsBySiS(Integer idStudent){
        return  repository.getNotSubjectsBySiS(idStudent);
    }

    public List<Student> getNotStudentsBySiS(Integer idSubject){
        return repository.getNotStudentsBySiS(idSubject);
    }
}
