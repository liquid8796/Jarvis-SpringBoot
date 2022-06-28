package com.userservice.service;

import com.userservice.dto.UserDTO;
import com.userservice.model.Response;

import java.util.List;

public interface UserService {

    Response<List<UserDTO>> getUsers();

    Response<UserDTO> getUserByUsername(String username);

    Response<UserDTO> getUserById(Long id);

    Response<UserDTO> addUser(UserDTO user);

    Response<UserDTO> updateUser(UserDTO user);

    void deleteUser(Long id);
}
