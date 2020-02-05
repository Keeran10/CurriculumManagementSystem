package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71703;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71703Repository extends JpaRepository<Section71703, Integer>{
    @Query(value = "SELECT * FROM section70713 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71703 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70713 WHERE id=?", nativeQuery = true)
    Section71703 findById(int id);
}
