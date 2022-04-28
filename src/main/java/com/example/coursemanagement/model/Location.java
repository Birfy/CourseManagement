package com.example.coursemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String building;

    @Column(nullable = false)
    private String room;

    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = { CascadeType.REMOVE })
    private Set<Course> courses;
}
