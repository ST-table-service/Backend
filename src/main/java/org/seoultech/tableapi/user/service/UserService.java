package org.seoultech.tableapi.user.service;

import org.seoultech.tableapi.auth.dto.RegisterRequest;
import org.seoultech.tableapi.user.entity.Role;
import org.seoultech.tableapi.user.entity.User;
import org.seoultech.tableapi.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest registerRequest) {
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = User.builder()
                .useremail(registerRequest.getUseremail())
                .username(registerRequest.getUsername())
                .password(encodedPassword)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
    public boolean isExists(String useremail) {
        return userRepository.existsByUseremail(useremail);
    }
}
