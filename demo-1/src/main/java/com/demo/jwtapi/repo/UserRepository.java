package com.demo.jwtapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.jwtapi.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
