package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

    @Query(value = "SELECT * FROM request WHERE id=?", nativeQuery = true)
    Request findByRequestId(Long id);
}
