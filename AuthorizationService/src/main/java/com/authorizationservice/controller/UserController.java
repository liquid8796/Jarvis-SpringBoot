package com.authorizationservice.controller;

import com.authorizationservice.dto.LoginDTO;
import com.authorizationservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authen")
public class UserController {

    @Autowired
    private Environment env;

    @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String login(@Valid @RequestBody LoginDTO dto){
        return "success";
    }

    @GetMapping("/ping")
    public String test(){
        return "UserService working on port " + env.getProperty("local.server.port");
    }
}
