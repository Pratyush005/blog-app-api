package com.snap.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snap.blog.payloads.UserDto;
import com.snap.blog.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() throws Exception {
        
    }

    @Test
    void updateUser() {
    }

    @Test
    void getAllUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("Pappi9");
        userDto.setEmail("Pappi9@gmail.com");
        userDto.setPassword("Pappi123");
        userDto.setAbout("I am Pappi Bhai");

        when(userService.getAllUser()).thenReturn(List.of(userDto));
        List<UserDto> mockUsers = List.of(userDto);

        when(userService.getAllUser()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userName").value("Pappi9"));

    }

    @Test
    void getUserById() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("Pappi9");
        userDto.setEmail("Pappi9@gmail.com");
        userDto.setPassword("Pappi123");
        userDto.setAbout("I am Pappi Bhai");

        when(userService.getUserById(1)).thenReturn(userDto);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.userName").value("Pappi9"))
                .andExpect(jsonPath("$.password").value("Pappi123"));
    }

    @Test
    void deleteUser() {
    }

    @Test
    void login() {
    }
}