package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section70713;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section70713Repository extends JpaRepository<Section70713, Integer>{
    @Query(value = "SELECT * FROM section70713 WHERE id=? AND is_active=1", nativeQuery = true)
    Section70713 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70713 WHERE id=?", nativeQuery = true)
    Section70713 findById(int id);
}
