package com.userservice.controller;

import com.userservice.dto.UserDTO;
import com.userservice.model.Response;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Response<List<UserDTO>> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/create")
    public Response<UserDTO> createUser(@RequestBody UserDTO user){
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Response<Boolean> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new Response<>(200, Boolean.TRUE);
    }

    @GetMapping("/header")
    public String getHeader(HttpServletRequest request){
        return request.getHeader("test");
    }
}
