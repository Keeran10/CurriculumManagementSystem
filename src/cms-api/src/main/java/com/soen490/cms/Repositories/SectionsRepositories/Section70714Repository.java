package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section70711;
import com.soen490.cms.Models.Sections.Section70714;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section70714Repository extends JpaRepository<Section70714, Integer>{

    @Query(value = "SELECT * FROM section70714 WHERE id=? AND is_active=1", nativeQuery = true)
    Section70714 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70714 WHERE id=?", nativeQuery = true)
    Section70714 findById(int id);
}
