package br.com.tspaschoal.forumalura.services;

import br.com.tspaschoal.forumalura.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var userByEmail = this.repository.findByEmail(email);
        if (!userByEmail.isPresent()) {
            throw new UsernameNotFoundException("User or Password are invalid!");
        }
        return userByEmail.get();
    }
}
