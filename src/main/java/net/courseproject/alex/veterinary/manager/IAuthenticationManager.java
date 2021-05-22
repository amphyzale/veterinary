package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;
import org.springframework.security.core.context.SecurityContext;

public interface IAuthenticationManager {
    LoginResponse login(AuthenticationRequest requestDto);
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
    boolean hasUserRole(SecurityContext context);
    boolean hasAdministratorRole(SecurityContext context);
    boolean hasDoctorRole(SecurityContext context);
    boolean hasGrandAdminRole(SecurityContext context);
    User getUser(SecurityContext context);
}
