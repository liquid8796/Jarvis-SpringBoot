package com.test.controller;

import com.test.dto.UserDTO;
import com.test.model.Response;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Response<List<UserDTO>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/header")
    public String getHeader(HttpServletRequest request){
        return request.getHeader("test");
    }
    @GetMapping("/user-header")
    public String getUserHeader(){
        return userService.getUserHeader();
    }
}
