package com.mini.erp.controllers.users;


import com.mini.erp.controllers.dto.LoginRequestDto;
import com.mini.erp.controllers.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String login(){
        return "Authentication successful!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequestDto loginRequestDto){
        log.info("Received login request: {}", loginRequestDto);

        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        log.info("Authentication principal:{} ", authentication.getPrincipal());
        log.info("Authentication credential:{} ", authentication.getCredentials());


        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        var authorities = userDetails.getAuthorities();
        log.info("Authorities that user has: {}", authorities);


        return new ResponseEntity<>("Hello " + userDetails.getUsername() + " you are authenticated user!!", HttpStatus.OK);
    }


    // TODO: Create login method which the role has been decided, for example if A register himself, then A's role is REGULAR_USER. But why?
    // TODO:
}
