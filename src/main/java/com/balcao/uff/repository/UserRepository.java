package com.balcao.uff.repository;

import com.balcao.uff.domain.User;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long> {
    User findByEmail(String username);
}
