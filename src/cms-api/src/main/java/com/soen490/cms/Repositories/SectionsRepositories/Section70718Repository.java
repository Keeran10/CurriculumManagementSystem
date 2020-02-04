package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section70718;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section70718Repository extends JpaRepository<Section70718, Integer>{
    @Query(value = "SELECT * FROM section70718 WHERE id=? AND is_active=1", nativeQuery = true)
    Section70718 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70718 WHERE id=?", nativeQuery = true)
    Section70718 findById(int id);
}
