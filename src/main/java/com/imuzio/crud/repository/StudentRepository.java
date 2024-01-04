package com.imuzio.crud.repository;

import com.imuzio.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Integer> {


}
