package com.userservice.controller;

import com.userservice.dto.TestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backup")
public class BackupController {

    @Value("${spring.datasource.url}")
    private String datasource;

    @GetMapping(value = "/currentversion", produces = { MediaType.APPLICATION_JSON_VALUE })
    public String getVersionDB(){
        String result = datasource.split("//")[1].split("/")[0].split(".")[0];
        return result;
    }
}
