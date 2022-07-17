package com.authorizationservice.service;

import com.authorizationservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO getUserByUsername(String username);
}
