package com.soen490.cms.Repositories;

import com.soen490.cms.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(int id);

    @Query(value = "SELECT * FROM user WHERE email=?1 AND password=?2", nativeQuery = true)
    User findByCredentials(String email, String password);
}
