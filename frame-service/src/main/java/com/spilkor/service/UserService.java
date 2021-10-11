package com.spilkor.service;

import com.spilkor.frame.model.User;
import com.spilkor.frame.model.dto.UserDTO;
import com.spilkor.frame.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class UserService extends FrameService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO login(String userName, String password){
        Assert.notNull(userName, "userName must not be null");
        Assert.notNull(password, "password must not be null");

        User user = userRepository.findUserByUserName(userName);
        if (user == null || !password.equals(user.getPassword())){
            return null;
        } else {
            return new UserDTO(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
