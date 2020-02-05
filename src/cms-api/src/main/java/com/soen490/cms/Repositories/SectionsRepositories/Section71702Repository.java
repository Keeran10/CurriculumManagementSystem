package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71702;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Section71702Repository extends JpaRepository<Section71702, Integer>{

    @Query(value = "SELECT * FROM section70712 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71702 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM section70712 WHERE id=?", nativeQuery = true)
    Section71702 findById(int id);
}
