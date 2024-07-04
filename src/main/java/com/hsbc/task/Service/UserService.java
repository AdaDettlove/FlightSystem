package com.hsbc.task.Service;

import com.hsbc.task.Model.User;

public interface UserService {
    boolean validateUser(String username, String password);
    User getUserByUsername(String username);
    boolean duplicateUsername(String username);
    boolean registerNewUser(User user);
    }
