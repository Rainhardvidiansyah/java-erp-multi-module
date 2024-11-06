package com.mini.erp.controllers.users;

import com.mini.erp.user.exception.EmailAndPasswordException;
import com.mini.erp.user.pojo.RegistrationRequest;
import com.mini.erp.user.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRegistrationController {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

    private final PasswordEncoder passwordEncoder;

    private final RegistrationService registrationService;


    @GetMapping("/")
    public String users(){
        log.info("Getting users page");
        return "Users page";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequest registrationRequest) throws EmailAndPasswordException {

        log.info("Received registration request: {}", registrationRequest);

        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        log.info("Password after encoded: {}", registrationRequest.getPassword());

        var registration = new RegistrationRequest(registrationRequest.getEmail(), registrationRequest.getPassword());

        var savedUser = registrationService.register(registration);

        return ResponseEntity.ok( "Registered user data:>> " +
                "email" + savedUser.getEmail() + "User Password: " + savedUser.getPassword());
    }



}
