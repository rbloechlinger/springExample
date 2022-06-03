package com.romeone.springapp.repository;

import java.util.Optional;

import com.romeone.springapp.model.ERole;
import com.romeone.springapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
