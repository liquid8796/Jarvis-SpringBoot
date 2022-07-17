package com.authorizationservice.serviceimpl;

import com.authorizationservice.dto.UserDTO;
import com.authorizationservice.feign.UserClient;
import com.authorizationservice.handler.JarvisException;
import com.authorizationservice.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.authorizationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Response<UserDTO> response = userClient.getUserByUsername(username);
        UserDTO dto = response.getData();
        System.out.println(">>>>>>>>>  "+dto.toString());

        return new User(dto.getEmail(), dto.getPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDTO getUserByUsername(String username) {

        Response<UserDTO> response = userClient.getUserByUsername(username);
        if(response.getData() == null) throw new JarvisException("Invalid username.", 401);
        UserDTO result = response.getData();

        return result;
    }
}
