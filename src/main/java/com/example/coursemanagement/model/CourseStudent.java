package com.example.coursemanagement.model;

import com.example.coursemanagement.common.CourseGrade;
import com.example.coursemanagement.common.CourseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "student_id", "course_id" }) })
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private CourseStatus courseStatus;

    @ManyToOne
//    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    private Course course;

    private CourseGrade courseGrade;
}
