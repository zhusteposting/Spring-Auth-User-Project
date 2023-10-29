package com.eposting.epost.service;

import com.eposting.epost.enumuation.AccountStatus;
import com.eposting.epost.enumuation.AuthProvider;
import com.eposting.epost.exception.ResourceNotFoundException;
import com.eposting.epost.mapper.ProfileMapper;
import com.eposting.epost.mapper.UserMapper;
import com.eposting.epost.model.Profile;
import com.eposting.epost.model.User;
import com.eposting.epost.model.dto.ProfileDTO;
import com.eposting.epost.model.dto.UserDTO;
import com.eposting.epost.payload.SignUpRequest;
import com.eposting.epost.repository.ProfileRepository;
import com.eposting.epost.repository.UserRepository;
import com.eposting.epost.security.oauth2.user.OAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser() {
        List<User> users  = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (userDTO.getAccountStatus().equals(AccountStatus.archive)) {
            deleteUser(user.getId());
            return null;
        }
        user.setAccountSettings(userDTO.getAccountSettings());
        user.setAccountType(userDTO.getAccountType());
        user.setAccountStatus(userDTO.getAccountStatus());
        user.setIsEmailAuthenticated(userDTO.getIsEmailAuthenticated());
        return userMapper.toDTO(user);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }

    public ProfileDTO getProfile(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));

        return profileMapper.toDTO(user.getProfile());
    }

    @Transactional
    public ProfileDTO updateProfile(String userId, ProfileDTO profileDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userId));
        Profile profile = profileRepository.save(profileMapper.toModel(profileDTO));
        user.setProfile(profile);
        userRepository.save(user);
        return profileMapper.toDTO(profile);
    }

    public User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setEmail(oAuth2UserInfo.getEmail());
        return registerNewUser(user);
    }

    public User registerNewUser(SignUpRequest signUpRequest) {
        // Creating user's account
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setSignupDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registerNewUser(user);
    }

    private User registerNewUser(User user) {
        user.setSignupDate(new Date());
        emailService.createEmail(user.getEmail());
        return userRepository.save(user);
    }

    public User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.save(existingUser);
    }
}
