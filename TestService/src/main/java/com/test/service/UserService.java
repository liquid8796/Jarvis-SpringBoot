package com.test.service;

import com.test.dto.UserDTO;
import com.test.model.Response;

import java.util.List;

public interface UserService {

    Response<List<UserDTO>> getUsers();
    String getUserHeader();
}
