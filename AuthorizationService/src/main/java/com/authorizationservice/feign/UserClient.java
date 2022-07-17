package com.authorizationservice.feign;

import com.authorizationservice.dto.UserDTO;
import com.authorizationservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${jarvis.application.user-service}", url = "https://jarvis-userservice.herokuapp.com")
public interface UserClient {

    @GetMapping(value = "/user/get-user-by-username")
    Response<UserDTO> getUserByUsername(@RequestParam("username") String username);

}
