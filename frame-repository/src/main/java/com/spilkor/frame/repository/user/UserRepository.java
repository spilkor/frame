package com.spilkor.frame.repository.user;

import com.spilkor.frame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    User findUserByUserName(String userName);

}