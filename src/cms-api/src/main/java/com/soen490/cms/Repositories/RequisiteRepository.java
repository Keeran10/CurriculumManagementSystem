package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Requisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RequisiteRepository extends JpaRepository<Requisite, Integer>{

    @Query(value = "SELECT * FROM requisite WHERE requisite_course_id=?", nativeQuery = true)
    Collection<Requisite> findAllOccurrencesOfCourseAsRequisite(int id);

}
