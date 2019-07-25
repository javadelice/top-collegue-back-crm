package dev.web.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import dev.web.crm.persistence.CollegueParticipantRepository;

@RestController
public class AuthController {
    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    private CollegueParticipantRepository collegueParticipantRepository;

    private PasswordEncoder passwordEncoder;

    public AuthController(CollegueParticipantRepository collegueParticipantRepository, PasswordEncoder passwordEncoder) {
        this.collegueParticipantRepository = collegueParticipantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
//
//    @PostMapping(value = "/auth")
//    public ResponseEntity<?> authenticate(@RequestBody CollegueAuth infos) {
//
//
//
//    }
}
