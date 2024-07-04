package com.hsbc.task.Service;

import com.hsbc.task.Model.User;
import com.hsbc.task.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public boolean registerNewUser(User user) {
        userRepository.save(user);
        return true;
    }

    public boolean duplicateUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
