package com.soen490.cms.Repositories;

import com.soen490.cms.Models.SubSection70719;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSection70719Repository  extends JpaRepository<SubSection70719, Integer> {

    @Query(value = "SELECT * FROM sub_section70719 WHERE id=? AND is_active=1", nativeQuery = true)
    SubSection70719 findBySubSectionId(int id);


    @Query(value = "SELECT * FROM sub_section70719 WHERE id=?", nativeQuery = true)
    SubSection70719 findById(int id);
}
