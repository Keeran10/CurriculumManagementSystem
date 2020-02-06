package com.soen490.cms.Repositories.SectionsRepositories;

import com.soen490.cms.Models.Sections.Section71401;
import org.springframework.data.jpa.repository.Query;

public interface Section71401Repository {

    @Query(value = "SELECT * FROM section71401 WHERE id=? AND is_active=1", nativeQuery = true)
    Section71401 findBySubSectionId(int id);

    @Query(value = "SELECT * FROM section71401 WHERE id=?", nativeQuery = true)
    Section71401 findById(int id);
}
