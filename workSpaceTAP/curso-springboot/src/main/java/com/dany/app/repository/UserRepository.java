package com.dany.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dany.app.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
