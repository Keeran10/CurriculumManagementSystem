package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section70717;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section70717Repository extends JpaRepository<Section70717, Integer>{

    @Query(value = "SELECT * FROM section70717 WHERE id=? AND is_active=1", nativeQuery = true)
    Section70717 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70717 WHERE id=?", nativeQuery = true)
    Section70717 findById(int id);
}
