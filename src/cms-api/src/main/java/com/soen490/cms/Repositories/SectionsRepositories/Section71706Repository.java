package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71706;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71706Repository extends JpaRepository<Section71706, Integer>{

    @Query(value = "SELECT * FROM section70716 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71706 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70716 WHERE id=?", nativeQuery = true)
    Section71706 findById(int id);
}
