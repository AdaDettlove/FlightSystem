package com.hsbc.task.Repo;

import com.hsbc.task.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserId(Long userId);
}
