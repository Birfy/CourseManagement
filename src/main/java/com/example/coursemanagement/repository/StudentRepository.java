package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT cs.course_id FROM course_student cs WHERE cs.student_id=:studentId AND cs.course_id=:courseId LIMIT 1", nativeQuery = true)
    CourseStudent getCoursesGrades(long studentId);
}
