package com.imuzio.crud.repository;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.StudentInSubject;
import com.imuzio.crud.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentInSubjectRepository extends JpaRepository<StudentInSubject,Integer> {

    @Query("Select sis from StudentInSubject sis where sis.student.id= ?1 and sis.subject.id = ?2")
    StudentInSubject getByStudentNSubject(Integer idStudent, Integer idSubject);

    @Query("Select sis.subject from StudentInSubject sis where sis.student.id= ?1")
    List<Subject> getSubjectsBySiS(Integer idStudent);

    @Query("Select sis.subject from StudentInSubject sis where sis.student.id= ?1")
    List<Subject> getNotSubjectsBySiS(Integer idStudent);

    @Query("Select sis.student from StudentInSubject sis where sis.subject.id = ?1")
    List<Student> getStudentsBySiS(Integer idSubject);

    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT sis.student.id FROM StudentInSubject sis WHERE sis.subject.id = ?1)")
    List<Student> getNotStudentsBySiS(Integer idSubject);

}
