package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71708;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71708Repository extends JpaRepository<Section71708, Integer>{
    @Query(value = "SELECT * FROM section70718 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71708 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70718 WHERE id=?", nativeQuery = true)
    Section71708 findById(int id);
}
