package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserTransformerProvider {

    public void transformDomainToDto(User domain, UserDto to) {
        to.setFirstName(domain.getFirstName());
        to.setLastName(domain.getLastName());
        to.setPatronymic(domain.getPatronymic());
        to.setEmail(domain.getEmail());
        to.setPhone(domain.getPhone());
        to.setUserPic(domain.getUserPic());
        to.setRoles(domain.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        to.setLastVisit(domain.getLastVisit());
        to.setStatus(domain.getStatus());
        to.setId(domain.getId());
        to.setFio(domain.getFio());
        to.setLocale(domain.getLocale());
        to.setGender(domain.getGender());
    }

    public void transformDtoToDomain(UserDto domain, User to) {
        to.setFirstName(domain.getFirstName());
        to.setLastName(domain.getLastName());
        to.setPatronymic(domain.getPatronymic());
        to.setFio(buildFIO(domain));
        to.setUserPic(domain.getUserPic());
        to.setPhone(domain.getPhone());
        to.setUsername(domain.getEmail());
        to.setEmail(domain.getEmail());
        to.setUserPic(domain.getUserPic());
        to.setLocale(domain.getLocale());
        to.setGender(domain.getGender());
    }

    private String buildFIO(UserDto guest) {
        return String.format("%s %s %s", guest.getFirstName(), guest.getLastName(), guest.getPatronymic());
    }
}
