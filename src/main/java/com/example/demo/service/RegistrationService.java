package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private static final String DEFAULT_ROLE  = "ROLE_USER";

    private RoleRepository roleRepository;
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(User user) {
        Role defaultRole = roleRepository.findByName(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);

//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);

        userRepository.save(user);
    }
}
