package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

    @Query(value = "SELECT * FROM course WHERE name=?1 AND number=?2", nativeQuery = true)
    Collection<Course> findByCourseNumber(String name, int number);

    @Query(value = "SELECT * FROM course WHERE id=?1 ", nativeQuery = true)
    Course findById(int number);
}
