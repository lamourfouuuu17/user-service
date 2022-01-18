package com.dancecamp.newdancecamp.repo;

import com.dancecamp.newdancecamp.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
} // наследование от JpaRepository
