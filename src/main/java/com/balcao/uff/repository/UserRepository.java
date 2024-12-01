package com.balcao.uff.repository;

import com.balcao.uff.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
