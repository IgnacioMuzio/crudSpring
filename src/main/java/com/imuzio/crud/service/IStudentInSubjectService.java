package com.imuzio.crud.service;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.StudentInSubject;
import com.imuzio.crud.entity.Subject;

import java.util.List;

public interface IStudentInSubjectService {

    public void delete(StudentInSubject sis);

    void save(StudentInSubject studentInSubject);

    public StudentInSubject getByStudentNSubject(Integer idStudent, Integer idSubject);

    public List<Subject> getSubjectsBySiS(Integer idStudent);

    public List<Student> getStudentsBySiS(Integer idSubject);

    List<Subject> getNotSubjectsBySiS(Integer idStudent);

    List<Student> getNotStudentsBySiS(Integer idSubject);

}
