package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import net.courseproject.alex.veterinary.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinary/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        UserResponse response = userService.getProfile();
        return responseEntity.body(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> findUser(
            @RequestParam(name = "id", required = false, defaultValue = "0") Long id,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "fio", required = false, defaultValue = "") String fio
    ) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<UserResponse> response = userService.findUser(id, email, fio);
        return responseEntity.body(response);
    }

    @GetMapping("/all_users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<UserResponse> response = userService.getAllUsers();
        return responseEntity.body(response);
    }

    @PatchMapping("/update_profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserRequest userRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        UserResponse response = userService.updateProfile(userRequest);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_user")
    public ResponseEntity<UserResponse> updateUserById(
            @RequestParam Long id,
            @RequestBody UserRequest userRequest
    ) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        UserResponse response = userService.updateUserById(id, userRequest);
        return responseEntity.body(response);
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<UserResponse> deleteUserById(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        UserResponse response = userService.deleteUserById(id);
        return responseEntity.body(response);
    }

    @DeleteMapping("/delete_profile")
    public ResponseEntity<UserResponse> deleteProfile() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        UserResponse response = userService.deleteProfile();
        return responseEntity.body(response);
    }
}
