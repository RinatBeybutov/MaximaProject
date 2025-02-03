package com.maxima.userService.repository;

import com.maxima.userService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

 }
