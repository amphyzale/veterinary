package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import net.courseproject.alex.veterinary.manager.IUserManager;
import net.courseproject.alex.veterinary.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserManager userManager;

    @Override
    public List<UserResponse> getAllUsers() {
        return userManager.getAllUsers();
    }

    @Override
    public UserResponse getProfile() {
        return userManager.getProfile();
    }

    @Override
    public List<UserResponse> findUser(String searchQuery) {
        return userManager.findUser(searchQuery);
    }

    @Override
    public UserResponse updateProfile(UserRequest userRequest) {
        return userManager.updateProfile(userRequest);
    }

    @Override
    public UserResponse updateUserById(Long id, UserRequest userRequest) {
        return userManager.updateUserById(id, userRequest);
    }

    @Override
    public UserResponse deleteProfile() {
        return userManager.deleteProfile();
    }

    @Override
    public UserResponse deleteUserById(Long id) {
        return userManager.deleteUserById(id);
    }
}
