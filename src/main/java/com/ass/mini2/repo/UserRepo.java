package com.ass.mini2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ass.mini2.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
