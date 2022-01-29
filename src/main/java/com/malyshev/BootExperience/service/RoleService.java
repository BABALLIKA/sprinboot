package com.malyshev.BootExperience.service;

import com.malyshev.BootExperience.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();

    Role getRole(String role);
}
