package com.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class GatewayController {

    @Autowired
    private Environment env;

    @GetMapping("/ping")
    public String test(){
        return "Gateway Server working on port " + env.getProperty("local.server.port");
    }
}
