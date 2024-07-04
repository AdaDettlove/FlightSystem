package com.hsbc.task.Service;

import com.hsbc.task.Model.User;
import com.hsbc.task.Repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegisterNewUser() {
        //given
        User user = new User(1L,
                "testUser",
                "testPassword",
                          Collections.emptyList());

        when(userRepository.save(user)).thenReturn(user);
        //when
        boolean result = userService.registerNewUser(user);

        //then
        assertTrue(result);
        verify(userRepository).save(user);
    }

    @Test
    public void testDuplicateUsername() {
        // given
        when(userRepository.findByUsername("existingUsername")).thenReturn(new User());

        // when
        boolean result = userService.duplicateUsername("existingUsername");

        // then
        assertTrue(result);
    }

    @Test
    public void testGetUserByUsername() {
        //given
        User mockUser = new User();
        mockUser.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        //when
        User result = userService.getUserByUsername("testUser");

        // then
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    public void testValidateUser_ValidCredentials() {
        // given
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockUser.setPassword("testPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        // when
        boolean result = userService.validateUser("testUser", "testPassword");

        // then
        assertTrue(result);
    }

    @Test
    public void testValidateUser_InvalidCredentials() {
        //given
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        // when
        boolean result = userService.validateUser("nonExistentUser", "wrongPassword");

        // then
        assertFalse(result);
    }
}