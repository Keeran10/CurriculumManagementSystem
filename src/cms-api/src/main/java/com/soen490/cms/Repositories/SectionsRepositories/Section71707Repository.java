package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71707;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71707Repository extends JpaRepository<Section71707, Integer>{

    @Query(value = "SELECT * FROM section71707 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71707 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section71707 WHERE id=?", nativeQuery = true)
    Section71707 findById(int id);
}
