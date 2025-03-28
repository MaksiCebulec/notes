package dotcom.si.notes.services;

import dotcom.si.notes.models.AppUser;
import dotcom.si.notes.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = usersRepo.findByUsername(username).orElseThrow();

        var authorities = appUser.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()))
                .toList();

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword()) // {noop} means no encoding for demo purposes
                .authorities(authorities)
                .build();
    }
}
