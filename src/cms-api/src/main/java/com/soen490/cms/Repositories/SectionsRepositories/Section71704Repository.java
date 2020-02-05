package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71704;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71704Repository extends JpaRepository<Section71704, Integer>{

    @Query(value = "SELECT * FROM section70714 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71704 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70714 WHERE id=?", nativeQuery = true)
    Section71704 findById(int id);
}
