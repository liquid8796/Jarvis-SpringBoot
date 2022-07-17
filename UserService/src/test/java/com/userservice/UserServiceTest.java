package com.userservice;

import com.userservice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet(){
        Assert.assertNotNull(userRepository.findAll());
    }
}
