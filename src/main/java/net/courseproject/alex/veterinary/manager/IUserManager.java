package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.UserResponse;

import java.util.List;

public interface IUserManager {
    List<UserResponse> getAllUsers();
    UserResponse getProfile();
    List<UserResponse> findUser(String searchQuery);
    UserResponse updateProfile(UserRequest userRequest);
    UserResponse updateUserById(Long id, UserRequest userRequest);
    UserResponse deleteProfile();
    UserResponse deleteUserById(Long id);
}
