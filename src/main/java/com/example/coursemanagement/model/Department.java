package com.example.coursemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = { CascadeType.REMOVE })
    private Set<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = { CascadeType.REMOVE })
    private Set<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = { CascadeType.REMOVE })
    private Set<Teacher> teachers;


}
