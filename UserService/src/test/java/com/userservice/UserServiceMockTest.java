package com.userservice;

import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import com.userservice.serviceimpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks // auto inject userRepository
    private UserService userService = new UserServiceImpl();

    @BeforeEach
    void setMockOutput(){
        when(userRepository.findAll()).thenReturn(anyList());
    }

    @DisplayName("Test Mock userService + userRepository")
    @Test
    void testGet() {
        Assert.assertNotNull(userService.getUsers());
    }
}
