package com.dds.bookmydoctor.repo;

import com.dds.bookmydoctor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
  User findByEmail(String email);
}
