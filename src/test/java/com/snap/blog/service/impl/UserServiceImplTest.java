package com.snap.blog.service.impl;

import com.snap.blog.entities.User;
import com.snap.blog.exception.ResourceNotFoundException;
import com.snap.blog.payloads.UserDto;
import com.snap.blog.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocking
    }

    @Test
    void testCreateUser() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("encodedPassword");
        userDto.setAbout("About the user");

        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setAbout("About the user");

        User savedUser = new User();
        savedUser.setUserName("testUser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setAbout("About the user");

        // Mock the ModelMapper
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(savedUser, UserDto.class)).thenReturn(userDto);

        // Mock the repository save method
        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // When
        UserDto result = userService.createUser(userDto);

        // Then
        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword()); // The password is encoded before saving
        assertEquals("About the user", result.getAbout());
    }

    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserName("updatedUser");
        userDto.setEmail("updatedUser@example.com");
        userDto.setPassword("updatedUserPassword");
        userDto.setAbout("Updated About of the user");

        User existingUser = new User();
        existingUser.setUserName("testUser");
        existingUser.setEmail("testUser@example.com");
        existingUser.setPassword("testUserPassword");
        existingUser.setAbout("About of the user");

        User updatedUser = new User();
        updatedUser.setUserName("updatedUser");
        updatedUser.setEmail("updatedUser@example.com");
        updatedUser.setPassword("updatedUserPassword");
        updatedUser.setAbout("Updated About of the user");

        when(userRepo.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(User.class))).thenReturn(updatedUser);

        when(modelMapper.map(updatedUser, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.updateUser(userDto, 1);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUserName());
        assertEquals("updatedUser@example.com", result.getEmail());
        assertEquals("updatedUserPassword", result.getPassword()); // The password is encoded before saving
        assertEquals("Updated About of the user", result.getAbout());

    }

    @Test
    void getUserByIdUserNotFound() {
        when(userRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    void getUserByIdUserFound() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("testUser");
        userDto.setEmail("testUser@example.com");
        userDto.setPassword("testUserPassword");
        userDto.setAbout("About of the user");

        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setUserName("testUser");
        existingUser.setEmail("testUser@example.com");
        existingUser.setPassword("testUserPassword");
        existingUser.setAbout("About of the user");

        when(userRepo.findById(1)).thenReturn(Optional.of(existingUser));
        when(modelMapper.map(existingUser, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        assertEquals("testUser@example.com", result.getEmail());
        assertEquals("testUserPassword", result.getPassword());
        assertEquals("About of the user", result.getAbout());
    }

    @Test
    void testDeleteUser_Success() {
        // Given
        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setUserName("testUser");
        existingUser.setEmail("test@example.com");
        existingUser.setPassword("password123");
        existingUser.setAbout("About the user");

        when(userRepo.findById(1)).thenReturn(Optional.of(existingUser));

        // When
        userService.deleteUser(1);

        // Then
        verify(userRepo, times(1)).delete(existingUser);
    }

}