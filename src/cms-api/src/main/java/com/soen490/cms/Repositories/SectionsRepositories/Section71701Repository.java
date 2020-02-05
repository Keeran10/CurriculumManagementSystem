package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71701;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71701Repository extends JpaRepository<Section71701, Integer> {

    @Query(value = "SELECT * FROM section70711 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71701 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70711 WHERE id=?", nativeQuery = true)
    Section71701 findById(int id);
}
