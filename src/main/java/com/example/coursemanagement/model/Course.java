package com.example.coursemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<CourseStudent> students;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    @JoinTable
    private Set<TimeSlot> timeslots;



//    public void enrollStudent(Student student) {
//        students.add(student);
//    }
//
//    public void dropStudent(Student student) {
//        students.remove(student);
//    }
}
