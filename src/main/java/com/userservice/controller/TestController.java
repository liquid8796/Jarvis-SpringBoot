package com.userservice.controller;

import com.userservice.dto.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/header", produces = { MediaType.APPLICATION_JSON_VALUE })
    public TestDTO getHeader(){
        TestDTO result = new TestDTO();
        return result;
    }


}
