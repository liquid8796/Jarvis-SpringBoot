package com.userservice.controller;

import com.userservice.dto.PoolDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/header")
    public PoolDTO getHeader(){
        PoolDTO result = new PoolDTO();
        result.setIp("sg.minexmr.com");
        result.setName("xmr-sgp");
        result.setPort(4444);
        result.setServer("sg.minexmr.com");
        result.setSuccess(true);
        return result;
    }
}
