package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section70716;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section70716Repository extends JpaRepository<Section70716, Integer>{

    @Query(value = "SELECT * FROM section70716 WHERE id=? AND is_active=1", nativeQuery = true)
    Section70716 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70716 WHERE id=?", nativeQuery = true)
    Section70716 findById(int id);
}
