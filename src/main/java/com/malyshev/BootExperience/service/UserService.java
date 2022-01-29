package com.malyshev.BootExperience.service;

import com.malyshev.BootExperience.model.Role;
import com.malyshev.BootExperience.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> allUsers();
    User getUserByName(String username);
    User getUserById(Long userId);
    void addUser(User user);
    void update(User user);
    void delete(User user);

    Set<Role> usersRole(User user);

}

