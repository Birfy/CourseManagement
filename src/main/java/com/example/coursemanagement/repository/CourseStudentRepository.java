package com.example.coursemanagement.repository;

import com.example.coursemanagement.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    @Query(value = "SELECT * FROM course_student cs WHERE cs.student_id=:studentId AND cs.course_id=:courseId LIMIT 1", nativeQuery = true)
    CourseStudent getCourseStudentById(long courseId, long studentId);
}
