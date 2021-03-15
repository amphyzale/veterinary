package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterRequestTransformerProviderImpl {

    public void transformDomainToDto(User domain, UserRegisterRequest to) {
        to.setFirstName(domain.getFirstName());
        to.setLastName(domain.getLastName());
        to.setPatronymic(domain.getPatronymic());
        to.setEmail(domain.getEmail());
        to.setPhone(domain.getPhone());
        to.setUserPic(domain.getUserPic());
        to.setLocale(domain.getLocale());
        to.setGender(UserRegisterRequest.Gender.fromValue(domain.getGender().name()));
    }

    public void transformDtoToDomain(UserRegisterRequest domain, User to) {
        to.setFirstName(domain.getFirstName());
        to.setLastName(domain.getLastName());
        to.setPatronymic(domain.getPatronymic());
        to.setFio(buildFIO(domain));
        to.setUserPic(domain.getUserPic());
        to.setPhone(domain.getPhone());
        to.setUsername(domain.getEmail());
        to.setLocale(domain.getLocale());
        to.setGender(Gender.fromValue(domain.getGender().name()));
    }

    private String buildFIO(UserRegisterRequest request) {
        return String.format("%s %s %s", request.getFirstName(), request.getLastName(), request.getPatronymic());
    }
}
