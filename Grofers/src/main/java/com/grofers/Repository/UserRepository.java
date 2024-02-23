package com.grofers.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grofers.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
}
