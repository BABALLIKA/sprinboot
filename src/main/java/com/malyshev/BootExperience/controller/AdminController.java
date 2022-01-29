package com.malyshev.BootExperience.controller;

import com.malyshev.BootExperience.model.Role;
import com.malyshev.BootExperience.model.User;
import com.malyshev.BootExperience.service.RoleService;
import com.malyshev.BootExperience.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String admin() {
        return "/admin/admin_page";
    }

    @GetMapping("/all_users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/admin/all_users";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("roles", roleService.allRoles());
        return "/admin/new";
    }

    @PostMapping()
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("roles") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.getRole(role));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin/all_users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.allRoles());
        return "/admin/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("roles") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.getRole(role));
        }
        User user = userService.getUserById(id);
        user.setUsername(username);
        user.setUsername(password);
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin/all_users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return "redirect:/admin/all_users";
    }
}
