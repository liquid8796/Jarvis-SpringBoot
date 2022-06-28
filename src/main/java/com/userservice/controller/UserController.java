package com.userservice.controller;

import com.userservice.dto.UserDTO;
import com.userservice.model.Response;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<List<UserDTO>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/get-user-by-username", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<UserDTO> getUserByUsername(@Valid @RequestParam("username") @Email String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response<UserDTO> createUser(@Valid @RequestBody UserDTO user){
        return userService.addUser(user);
    }

    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response<UserDTO> updateUser(@Valid @RequestBody UserDTO user){
        return userService.updateUser(user);
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

    @GetMapping("/ping")
    public String test(){
        return "UserService working on port " + env.getProperty("local.server.port");
    }
}
