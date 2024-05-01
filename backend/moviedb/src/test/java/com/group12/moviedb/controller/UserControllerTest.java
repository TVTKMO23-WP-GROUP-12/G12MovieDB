package com.group12.moviedb.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.services.UserService;


public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock 
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test end.");
    }

    @Test
    public void shouldSuccessfullyAddOneUser() {
        // Given
        User user = new User(
            123,
            "Dummy", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
            );

        User addedUser = new User(
            123, 
            "Dummy", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
            );
        
            addedUser.setCreatedAt(null);
            addedUser.setUpdatedAt(null);

        // Mock the calls
        when(passwordEncoder.encode("pass123")).thenReturn("hashedPassword");
        when(userRepository.save(user)).thenReturn(addedUser);

        // When
        User result = userController.addOneUser(user);

        // Then
        verify(userRepository, times(1)).save(user);

        // Verify that the returned user object is not null
        assertNotNull(result);

    }

    @Test
    public void shouldSuccessfullyUpdateOneUser() {
        
        // Given
        Integer userId = 123;
        User userToUpdate = new User(
            userId, 
            "UpdatedDummy", 
            "updated@email.com", 
            "updatedPass123", 
            "updatedDescription", 
            "updatedProfilePicture"
        );

        // Mocking
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToUpdate));
        when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);
    
        // When
        User updatedUser = userRepository.save(userToUpdate);

        // Then
        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals("UpdatedDummy", updatedUser.getUsername());
        assertEquals("updated@email.com", updatedUser.getEmail());
        assertEquals("updatedPass123", updatedUser.getPassword());
        assertEquals("updatedDescription", updatedUser.getUserDescription());
        assertEquals("updatedProfilePicture", updatedUser.getProfilePicture());
}

    @Test
    public void testShouldSuccessfullyFindOneUsername() {
        // Given
        String username = "Dummy";
        User user = new User();
        user.setUsername(username);
    
        when(userRepository.findByUsername(username)).thenReturn(user);
    
        // When
        User retrievedUser = userRepository.findByUsername(username);
    
        // Then
        assertNotNull(retrievedUser);
        assertEquals(username, retrievedUser.getUsername());
    }

    @Test
    public void testShouldSuccessfullyFindOneUserById() {
    // Given
    Integer userId = 123;
    User user = new User();
    user.setId(userId);
    Optional<User> optionalUser = Optional.of(user);
  
    when(userRepository.findById(userId)).thenReturn(optionalUser);
  
    // When
    Optional<User> retrievedUserOptional = userRepository.findById(userId);
  
    // Then
    assertTrue(retrievedUserOptional.isPresent());
    assertEquals(userId, retrievedUserOptional.get().getId());
}

    @Test
    public void testShouldSuccessfullyFindAllUsers() {
          // Given
          User user1 = new User(
            123,
            "Dummy", 
            "dummy@email.com", 
            "pass123", 
            "description", 
            "something"
        );
        User user2 = new User(
            124,
            "Person", 
            "person@email.com", 
            "pass124", 
            "description1", 
            "something1"
        );
          User user3 = new User(
            125,
            "Someone", 
            "someone@email.com", 
            "pass125", 
            "description2", 
            "something2"
        );
          List<User> expectedUsers = Arrays.asList(user1, user2, user3);
  
          // Mock the behavior of userRepository.findAll() to return the list of users
          when(userRepository.findAll()).thenReturn(expectedUsers);
  
          // When
          List<User> actualUsers = userRepository.findAll();
  
          // Then
          assertEquals(expectedUsers.size(), actualUsers.size());
          for (int i = 0; i < expectedUsers.size(); i++) {
              assertEquals(expectedUsers.get(i).getId(), actualUsers.get(i).getId());
              assertEquals(expectedUsers.get(i).getUsername(), actualUsers.get(i).getUsername());
              assertEquals(expectedUsers.get(i).getEmail(), actualUsers.get(i).getEmail());
              assertEquals(expectedUsers.get(i).getPassword(), actualUsers.get(i).getPassword());
          }
      }
  

}





