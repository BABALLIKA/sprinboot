package com.malyshev.BootExperience.config;

import com.malyshev.BootExperience.model.Role;
import com.malyshev.BootExperience.model.User;
import com.malyshev.BootExperience.repository.RoleRepo;
import com.malyshev.BootExperience.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class PostConstructConfig {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public PostConstructConfig(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void addRolesAndUsersInDB() {
        checkRole("ROLE_ADMIN");
        checkRole("ROLE_USER");
        checkAdmin();
        checkUser();
    }

    private void checkRole(String roleName) {
        if(!roleRepo.existsByName(roleName)){
            roleRepo.save(new Role(roleName));
        }
    }

    private void checkAdmin() {
        if(!userRepo.existsByUsername("admin")){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepo.findByName("ROLE_ADMIN"));
        roleSet.add(roleRepo.findByName("ROLE_USER"));
        userRepo.save(new User("admin", passwordEncoder().encode("admin"), roleSet));
        }
    }

    private void checkUser() {
        if(!userRepo.existsByUsername("user")){
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roleRepo.findByName("ROLE_USER"));
            userRepo.save(new User("user", passwordEncoder().encode("user"), roleSet));
        }
    }
}
