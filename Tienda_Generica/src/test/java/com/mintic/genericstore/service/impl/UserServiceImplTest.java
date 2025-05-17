
package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.ERole;
import com.mintic.genericstore.model.Role;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.repository.RoleRepository;
import com.mintic.genericstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_returnsListOfUsers() {
        User user = new User();
        user.setIdNumber(1L);
        user.setFullName("Test User");

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.getUsers();

        assertFalse(result.isEmpty());
        assertEquals("Test User", result.get(0).getFullName());
    }

    @Test
    void getUserById_returnsUser() {
        User user = new User();
        user.setIdNumber(1L);
        user.setFullName("Test User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.getUserById(1L);

        assertEquals("Test User", result.getFullName());
    }

    @Test
    void getUserById_throwsNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void saveUser_success() throws GenericStoreException {
        SignupRequestDTO request = new SignupRequestDTO();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setFullName("Test User");
        request.setRoles(Set.of("user"));

        Role role = new Role();
        role.setName(ERole.ROLE_USER);

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponseDTO result = userService.saveUser(request);

        assertEquals("Test User", result.getFullName());
    }

    @Test
    void saveUser_throwsExceptionWhenUsernameExists() {
        SignupRequestDTO request = new SignupRequestDTO();
        request.setUsername("testuser");
        when(userRepository.existsByUsername("testuser")).thenReturn(true);
        assertThrows(GenericStoreException.class, () -> userService.saveUser(request));
    }

    @Test
    void deleteUser_success() {
        User user = new User();
        user.setIdNumber(1L);
        user.setFullName("Test User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.deleteUser(1L);

        assertEquals("Test User", result.getFullName());
        verify(userRepository).deleteById(1L);
    }
}
