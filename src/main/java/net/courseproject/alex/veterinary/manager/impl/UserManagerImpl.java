package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserResponseTransformerProvider;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.manager.IUserManager;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.security.jwt.JwtUser;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager {

    private final UserRepository userRepository;
    private final IAuthenticationManager authenticationManager;
    private final UserResponseTransformerProvider transformer;

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
        Optional<User> user = userRepository.findById(id);
        return transformer.transformToResponse(user.orElseThrow(UnknownError::new));
    }

    @Override
    public List<UserResponse> findUser(Long id, String email, String fio) {
        return userRepository.findUsersByIdOrEmailContainingOrFioContaining(id, email, fio)
                .stream()
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
        user.setGender(userRequest.getGender());
        LocalDateTime now = LocalDateTime.now();
        user.setUpdated(now);
        user.setLastVisit(now);
        user.setFio(buildFIO(userRequest));
    }

    private String buildFIO(UserRequest userRequest) {
        return String.format("%s %s %s", userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPatronymic());
    }

}
