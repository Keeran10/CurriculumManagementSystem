package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section707110;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section707110Repository extends JpaRepository<Section707110, Integer> {
    @Query(value = "SELECT * FROM section707110 WHERE id=? AND is_active=1", nativeQuery = true)
    Section707110 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section707110 WHERE id=?", nativeQuery = true)
    Section707110 findById(int id);
}
