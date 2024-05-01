package com.group12.moviedb.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;


import com.group12.moviedb.controller.UserController;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock 
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test end.");
    }

    @Test
    public void shouldSuccessfullySaveTheUser() {
        // Given
        User user = new User(
            123,
            "Dummy", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
            );

        User savedUser = new User(
            123, 
            "Dummy", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
            );
        
            savedUser.setCreatedAt(null);

        // Mock the calls
        when(passwordEncoder.encode("pass123")).thenReturn("hashedPassword");
        when(userRepository.save(user)).thenReturn(savedUser);

        // When
        User result = userService.saveUser(user);

        // Then
        verify(userRepository, times(1)).save(user);

        // Verify that the returned user object is not null
        assertNotNull(result);

    }

    @Test
    public void shouldSuccessfullyGetUserByUsername() {
        // Given
        User user = new User(
            123,
            "Person", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
        );

        when(userRepository.findByUsername("Dummy")).thenReturn(user);

        //When
        User retrievedUser = userService.getUserByUsername("Dummy");

        // Then
        assertNotNull(retrievedUser);

        assertEquals(123, retrievedUser.getId());
        assertEquals("Person", retrievedUser.getUsername());
        assertEquals("dummy@email.com", retrievedUser.getEmail());
        assertEquals("pass123", retrievedUser.getPassword());
        assertEquals("description", retrievedUser.getUserDescription());
        assertEquals("something", retrievedUser.getProfilePicture());
    }

    @Test
    public void testShouldSuccessfullyDeleteUser() {
        // Given
        Integer userId = 123;

        // When
        userService.deleteUser(userId);
    
        // Then
        // Verify that userRepository.deleteById was called with the correct user ID
        verify(userRepository).deleteById(userId);
    }
}