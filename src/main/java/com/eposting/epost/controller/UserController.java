package com.eposting.epost.controller;

import com.eposting.epost.exception.ResourceNotFoundException;
import com.eposting.epost.model.User;
import com.eposting.epost.model.dto.ProfileDTO;
import com.eposting.epost.model.dto.UserDTO;
import com.eposting.epost.payload.ChangePasswordRequest;
import com.eposting.epost.repository.UserRepository;
import com.eposting.epost.security.CurrentUser;
import com.eposting.epost.security.UserPrincipal;
import com.eposting.epost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public UserDTO updateUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userPrincipal.getId(), userDTO);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void updateUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ProfileDTO getProfile(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getProfile(userPrincipal.getId());
    }

    @PutMapping("/profile")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ProfileDTO updateProfile(@CurrentUser UserPrincipal userPrincipal, @RequestBody ProfileDTO profileDTO) {
        return userService.updateProfile(userPrincipal.getId(), profileDTO);
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request.getOldPass(), request.getNewPass(), userPrincipal.getId());
    }

    @PostMapping("/{id}/change-password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changePasswordAdmin(@PathVariable String id,
                                            @RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request.getOldPass(), request.getNewPass(), id);
    }
}