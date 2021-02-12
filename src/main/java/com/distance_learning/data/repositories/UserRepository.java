package com.distance_learning.data.repositories;

import com.distance_learning.data.models.Role;
import com.distance_learning.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findByAuthorities(Role roles);
}
