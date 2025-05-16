package com.snap.blog.repository;

import com.snap.blog.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private User user;

    @BeforeEach
    void setUp() {
        // Insert row in user table
        user = new User();
        user.setUserName("Papun");
        userRepo.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepo.delete(user);
    }

    @Test
    void findByuserName() {
        User userName = userRepo.findByuserName("Papun");
        assertNotNull(userName);
        assertEquals(user.getUserName(), userName.getUserName());
    }
}