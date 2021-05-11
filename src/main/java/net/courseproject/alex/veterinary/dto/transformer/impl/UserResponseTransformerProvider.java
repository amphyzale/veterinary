package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserResponseTransformerProvider {

    public UserResponse transformToResponse(User domain) {
        return new UserResponse()
                .setFirstName(domain.getFirstName())
                .setLastName(domain.getLastName())
                .setPatronymic(domain.getPatronymic())
                .setEmail(domain.getEmail())
                .setPhone(domain.getPhone())
                .setUserPic(domain.getUserPic())
                .setRoles(domain.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .setLastVisit(domain.getLastVisit())
                .setStatus(domain.getStatus())
                .setId(domain.getId())
                .setFio(domain.getFio())
                .setLocale(domain.getLocale())
                .setGender(domain.getGender());
    }
}
