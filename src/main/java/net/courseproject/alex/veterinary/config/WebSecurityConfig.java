package net.courseproject.alex.veterinary.config;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.security.jwt.JwtConfigurer;
import net.courseproject.alex.veterinary.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String USER_ROLE = "USER";
    public static final String GRAND_ADMIN_ROLE = "GRAND_ADMIN";
    public static final String ADMINISTRATOR_ROLE = "ADMINISTRATOR";
    public static final String DOCTOR_ROLE = "DOCTOR";
    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_ENDPOINT = "/veterinary/v1/auth/login";
    private static final String REGISTER_ENDPOINT = "/veterinary/v1/auth/register";
    private static final String PROFILE_ENDPOINT = "/veterinary/v1/users/**";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf()
                .and()
                .cors()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(REGISTER_ENDPOINT).permitAll()
                .antMatchers(PROFILE_ENDPOINT).hasAnyAuthority(USER_ROLE, GRAND_ADMIN_ROLE, ADMINISTRATOR_ROLE, DOCTOR_ROLE)
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
