package com.imuzio.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "LastName is mandatory")
    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<StudentInSubject> subjects;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
