package com.userservice.controller;

import com.userservice.dto.UserDTO;
import com.userservice.handler.JarvisException;
import com.userservice.model.Response;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<List<UserDTO>> getUsers(){
        return userService.getUsers();
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

    @GetMapping("/test")
    public String test(){
        throw new JarvisException("Internal error", 500);
    }
}
