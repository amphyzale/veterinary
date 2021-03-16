package net.courseproject.alex.veterinary.security;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.security.jwt.JwtUserFactory;
import net.courseproject.alex.veterinary.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + name + " not found!");
        }

        return JwtUserFactory.create(user);
    }
}
