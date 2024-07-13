package com.niko.beckend_practice.repository;

import com.niko.beckend_practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
