package org.spring.bookitrestapi.security;

import org.spring.bookitrestapi.model.AppUser;
import org.spring.bookitrestapi.repository.AppUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepo appUserRepo;

    public CustomUserDetailsService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetalsObject(appUser);
    }
}
