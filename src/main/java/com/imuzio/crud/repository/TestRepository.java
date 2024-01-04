package com.imuzio.crud.repository;

import com.imuzio.crud.entity.Student;
import com.imuzio.crud.entity.Subject;
import com.imuzio.crud.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {

    @Query("SELECT t FROM Test t WHERE t.student.id= ?1")
    List<Test> getTestsByStudentId(Integer id);

    @Query("SELECT DISTINCT t.subject FROM Test t WHERE t.student.id = ?1")
    List <Subject> getSubjectByTestStudentId(Integer id);

    @Query("SELECT t FROM Test t WHERE t.subject.id= ?1")
    List<Test> getTestsBySubjectId(@Param("id")Integer id);

    @Query("SELECT DISTINCT t.student FROM Test t WHERE t.subject.id= ?1")
    List <Student> getStudentByTestSubjectId(Integer id);
}
