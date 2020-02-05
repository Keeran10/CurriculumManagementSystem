package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71705;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71705Repository extends JpaRepository<Section71705, Integer> {

    @Query(value = "SELECT * FROM section71705 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71705 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section71705 WHERE id=?", nativeQuery = true)
    Section71705 findById(int id);
}
