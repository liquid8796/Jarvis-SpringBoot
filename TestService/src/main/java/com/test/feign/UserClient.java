package com.test.feign;

import com.test.dto.UserDTO;
import com.test.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-client", url = "http://localhost:9090/")
public interface UserClient {
    @GetMapping(value = "/user/list")
    Response<List<UserDTO>> getUsers();

    @GetMapping(value = "/user/header")
    String getUserHeader();
}
