package com.test.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entities.users;

public interface userrepos extends JpaRepository<users, Integer> {

	Optional<users> findByUsername(String username);


}
