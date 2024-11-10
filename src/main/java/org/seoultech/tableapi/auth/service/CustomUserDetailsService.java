package org.seoultech.tableapi.auth.service;

import org.seoultech.tableapi.auth.dto.CustomUserDetails;
import org.seoultech.tableapi.user.entity.User;
import org.seoultech.tableapi.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = userRepository.findByUseremail(useremail);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}
