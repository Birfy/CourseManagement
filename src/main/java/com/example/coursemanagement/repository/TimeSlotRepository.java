package com.example.coursemanagement.repository;

import com.example.coursemanagement.common.CourseTime;
import com.example.coursemanagement.common.Weekday;
import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Query(value = "SELECT * FROM time_slot ts WHERE ts.weekday=:weekday AND ts.course_id=:courseTime LIMIT 1", nativeQuery = true)
    TimeSlot getCourseStudentById(Weekday weekday, CourseTime courseTime);


}
