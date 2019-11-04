package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer>{
    Degree findByName(String name);

    @Query(value = "SELECT * FROM degree d INNER JOIN required_course ON d.id = required_course.degree_id WHERE required_course.course_id=?1", nativeQuery = true)
    Collection<Degree> findDegreeByRequiredCourseId(int course_id);

    @Query(value = "SELECT * FROM degree d INNER JOIN elective_course ON d.id = elective_course.degree_id WHERE elective_course.course_id=?1", nativeQuery = true)
    Collection<Degree> findDegreeByElectiveCourseId(int course_id);
}
