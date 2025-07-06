package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.controller.DTO.RegistrationDTO;
import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegController {
    private final AppUserService appUserService;
    public RegController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @PostMapping("/newUser")
    public void registerUser(@RequestBody RegistrationDTO newUser) {
        AppUser appUser = new AppUser();
        appUser.setUsername(newUser.getUsername());
        appUser.setEmail(newUser.getEmail());
        appUser.setPassword(newUser.getPassword());
        appUserService.addUser(appUser);
    }
}
