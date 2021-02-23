package net.courseproject.alex.veterinary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Accessors(chain = true)
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String fio;
    private String userPic;
    private String email;
    private Status status;
    private String phone;
    private LocalDateTime lastVisit;
    private List<String> roles;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setFio(fio);
        user.setUserPic(userPic);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLastVisit(lastVisit);
        user.setRoles(roles.stream().map(Role::new).collect(Collectors.toList()));
        user.setStatus(status);
        return user;
    }

    public static UserDto fromUser(User user) {
        return new UserDto().setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPatronymic(user.getPatronymic())
                .setFio(user.getFio())
                .setUserPic(user.getUserPic())
                .setEmail(user.getEmail())
                .setStatus(user.getStatus())
                .setPhone(user.getPhone())
                .setLastVisit(user.getLastVisit())
                .setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
    }
}
