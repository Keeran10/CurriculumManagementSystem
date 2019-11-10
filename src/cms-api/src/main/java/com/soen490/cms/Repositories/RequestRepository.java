package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

    @Query(value = "SELECT * FROM request WHERE id=?1", nativeQuery = true)
    Request findByRequestId(int id);

    @Query(value = "SELECT * FROM request WHERE user_id=?1 AND package_id=?2 AND original_id=?3", nativeQuery = true)
    Request findByTripleId(int user_id, int package_id, int original_id);
}
