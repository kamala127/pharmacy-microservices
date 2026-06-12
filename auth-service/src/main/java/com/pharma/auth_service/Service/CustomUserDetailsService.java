package com.pharma.auth_service.Service;

import com.pharma.auth_service.Entity.User;
import com.pharma.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {


        User user =
                userRepository.findByName(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User Not Found"));

        return org.springframework.security
                .core.userdetails.User
                .builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().getRoleName())
                .build();
    }
}
