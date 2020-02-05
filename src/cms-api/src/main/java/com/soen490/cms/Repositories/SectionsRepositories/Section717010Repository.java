package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section717010;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section717010Repository extends JpaRepository<Section717010, Integer> {
    @Query(value = "SELECT * FROM section707110 WHERE id=? AND is_active=1", nativeQuery = true)
    Section717010 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section707110 WHERE id=?", nativeQuery = true)
    Section717010 findById(int id);
}
