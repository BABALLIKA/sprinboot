package com.malyshev.BootExperience.service;

import com.malyshev.BootExperience.model.Role;
import com.malyshev.BootExperience.repository.RoleRepo;
import com.malyshev.BootExperience.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRole(String role) {
        return roleRepo.findByName(role);
    }
}
