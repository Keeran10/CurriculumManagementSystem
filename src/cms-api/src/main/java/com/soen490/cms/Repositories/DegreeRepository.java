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

    @Query(value = "SELECT * FROM degree d INNER JOIN degree_requirement ON d.id = degree_requirement.degree_id WHERE degree_requirement.course_id=?1 AND core LIKE '% Core'", nativeQuery = true)
    Collection<Degree> findDegreeByRequiredCourseId(int course_id);

    @Query(value = "SELECT * FROM degree d INNER JOIN degree_requirement ON d.id = degree_requirement.degree_id WHERE degree_requirement.course_id=?1 AND core NOT LIKE '% Electives'", nativeQuery = true)
    Collection<Degree> findDegreeByElectiveCourseId(int course_id);

    @Query(value = "SELECT SUM(c.credits) FROM course c INNER JOIN degree_requirement ON c.id = degree_requirement.course_id WHERE degree_requirement.core=?1 AND c.is_active=1 ", nativeQuery = true)
    double findCreditsTotalOfCoreProgram(String name);

}
