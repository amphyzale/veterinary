package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.*;
import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.DoctorTransformerProvider;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserResponseTransformerProvider;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.manager.IUserManager;
import net.courseproject.alex.veterinary.repository.DoctorRepository;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.security.jwt.JwtUser;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager {

    private final UserRepository userRepository;
    private final IAuthenticationManager authenticationManager;
    private final UserResponseTransformerProvider transformer;
    private final DoctorRepository doctorRepository;
    private final DoctorTransformerProvider doctorTransformerProvider;

    @Override
    public List<UserResponse> getAllUsers() {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        return userRepository.findAll()
                .stream()
                .map(transformer::transformToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Long id = ((JwtUser) context.getAuthentication().getPrincipal()).getId();
        User user = userRepository.findById(id).orElseThrow(UnknownError::new);
        UserResponse result = transformer.transformToResponse(user);
        if (user.getRoles().stream().map(Role::getName).anyMatch("DOCTOR"::contains)) {
            Doctor doctor = doctorRepository.findByEmail(user.getEmail());
            DoctorResponse response = doctorTransformerProvider.transformDomainTo(doctor);
            result.setDoctorResponse(response);
        }
        return result;
    }

    @Override
    public List<UserResponse> findUser(String searchQuery) {
        if (NumberUtils.isDigits(searchQuery)) {
            return Collections.singletonList(
                    transformer.transformToResponse(
                            userRepository.findById(Long.parseLong(searchQuery)).orElseThrow(UnknownError::new))
            );
        }
        return userRepository.findAll()
                .stream()
                .filter(i -> doFilter(searchQuery, i))
                .map(transformer::transformToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateProfile(UserRequest userRequest) {
        SecurityContext context = SecurityContextHolder.getContext();
        Long id = ((JwtUser) context.getAuthentication().getPrincipal()).getId();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userData = user.get();
            updateUserData(userData, userRequest);
            userRepository.save(userData);
            return transformer.transformToResponse(userData);
        }
        throw new UnknownError();
    }

    @Override
    public UserResponse updateUserById(Long id, UserRequest userRequest) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userData = user.get();
            updateUserData(userData, userRequest);
            userRepository.save(userData);
            return transformer.transformToResponse(userData);
        }
        throw new UnknownError();
    }

    @Override
    public UserResponse deleteProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Long id = ((JwtUser) context.getAuthentication().getPrincipal()).getId();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userData = user.get();
            userData.setStatus(Status.DELETED);
            userRepository.save(userData);
            return transformer.transformToResponse(userData);
        }
        throw new UnknownError();
    }

    @Override
    public UserResponse deleteUserById(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userData = user.get();
            userData.setStatus(Status.DELETED);
            userRepository.save(userData);
            return transformer.transformToResponse(userData);
        }
        throw new UnknownError();
    }


    private void updateUserData(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPatronymic(userRequest.getPatronymic());
        user.setUserPic(userRequest.getUserPic());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setLocale(userRequest.getLocale());
        user.setGender(Gender.valueOf(userRequest.getGender().name()));
        LocalDateTime now = LocalDateTime.now();
        user.setUpdated(now);
        user.setLastVisit(now);
        user.setFio(buildFIO(userRequest));
    }

    private String buildFIO(UserRequest userRequest) {
        return String.format("%s %s %s", userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPatronymic());
    }

    private boolean doFilter(String searchQuery, User i) {
        return i.getEmail() != null && i.getEmail().contains(searchQuery) || i.getFio() != null && i.getFio().contains(searchQuery);
    }

}
