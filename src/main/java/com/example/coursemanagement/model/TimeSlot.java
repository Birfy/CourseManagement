package com.example.coursemanagement.model;

import com.example.coursemanagement.common.CourseTime;
import com.example.coursemanagement.common.Weekday;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Weekday weekday;

    private CourseTime courseTime;

    @JsonIgnore
    @ManyToMany(mappedBy = "timeslots", cascade = { CascadeType.REMOVE })
    Set<Course> courses;
}
