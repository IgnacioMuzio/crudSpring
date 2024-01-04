package com.imuzio.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private Set<StudentInSubject> students;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private Set<Test> tests;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
