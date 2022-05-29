package com.test.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dto.UserDTO;
import com.test.entity.Role;
import com.test.entity.User;
import com.test.feign.UserClient;
import com.test.model.Response;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Override
    public Response<List<UserDTO>> getUsers() {
        return userClient.getUsers();
    }

    @Override
    public String getUserHeader() {
        return userClient.getUserHeader();
    }
}
