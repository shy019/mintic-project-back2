package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import com.mintic.genericstore.model.ERole;
import com.mintic.genericstore.model.Role;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.model.UserAuthentication;
import com.mintic.genericstore.repository.RoleRepository;
import com.mintic.genericstore.repository.UserRepository;
import com.mintic.genericstore.config.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void authenticateUser_success() {
        // Arrange
        LoginRequestDTO login = new LoginRequestDTO("user1", "pass1");
        @SuppressWarnings("unchecked")
        Authentication auth = mock(Authentication.class);
        UserAuthentication principal = new UserAuthentication(
                123L, "user1", "Full Name", "user1@example.com", "", List.of()
        );
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(principal);
        when(jwtUtils.generateJwtToken(auth)).thenReturn("jwt-token");

        // Act
        ResponseEntity<JwtResponse> response = authService.authenticateUser(login);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        JwtResponse body = response.getBody();
        assertNotNull(body);
        assertEquals("jwt-token", body.getToken());
        assertEquals(principal.getIdNumber(), body.getId());
        assertEquals(principal.getUsername(), body.getUsername());
        assertEquals(principal.getEmail(), body.getEmail());
        verify(authenticationManager, times(1)).authenticate(any());
        assertEquals(SecurityContextHolder.getContext().getAuthentication(), auth);
    }

    @Test
    void registerUser_usernameTaken() {
        // Arrange
        SignupRequestDTO signup = new SignupRequestDTO();
        signup.setUsername("user1");
        signup.setEmail("email@example.com");

        when(userRepository.existsByUsername("user1")).thenReturn(true);

        // Act
        ResponseEntity<MessageResponse> response = authService.registerUser(signup);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(USERNAME_IS_TAKEN, response.getBody().getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_emailTaken() {
        // Arrange
        SignupRequestDTO signup = new SignupRequestDTO();
        signup.setUsername("user1");
        signup.setEmail("email@example.com");

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(userRepository.existsByEmail("email@example.com")).thenReturn(true);

        // Act
        ResponseEntity<MessageResponse> response = authService.registerUser(signup);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(EMAIL_IN_USE, response.getBody().getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_defaultsRoleAndSuccess() {
        // Arrange
        SignupRequestDTO signup = new SignupRequestDTO();
        signup.setUsername("user1");
        signup.setEmail("email@example.com");
        signup.setIdNumber(123L);
        signup.setPassword("pass");
        signup.setFullName("Full Name");

        Role userRole = new Role();
        userRole.setName(ERole.ROLE_USER);

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(userRepository.existsByEmail("email@example.com")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));

        // Act
        ResponseEntity<MessageResponse> response = authService.registerUser(signup);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(USER_REGISTERED, response.getBody().getMessage());
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User saved = captor.getValue();
        assertEquals("encodedPass", saved.getPassword());
        assertTrue(saved.getRoles().contains(userRole));
    }

    @Test
    void registerUser_withCustomRolesAndSuccess() {
        // Arrange
        SignupRequestDTO signup = new SignupRequestDTO();
        signup.setUsername("user1");
        signup.setEmail("email@example.com");
        signup.setIdNumber(123L);
        signup.setPassword("pass");
        signup.setFullName("Full Name");
        signup.setRoles(Set.of("admin", "mod", "other"));

        Role adminRole = new Role(); adminRole.setName(ERole.ROLE_ADMIN);
        Role modRole = new Role(); modRole.setName(ERole.ROLE_MODERATOR);
        Role userRole = new Role(); userRole.setName(ERole.ROLE_USER);

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(userRepository.existsByEmail("email@example.com")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("encodedPass");
        when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(adminRole));
        when(roleRepository.findByName(ERole.ROLE_MODERATOR)).thenReturn(Optional.of(modRole));
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(userRole));

        // Act
        ResponseEntity<MessageResponse> response = authService.registerUser(signup);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(USER_REGISTERED, response.getBody().getMessage());
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        Set<Role> rolesSaved = captor.getValue().getRoles();
        assertTrue(rolesSaved.contains(adminRole));
        assertTrue(rolesSaved.contains(modRole));
        assertTrue(rolesSaved.contains(userRole));
    }
}