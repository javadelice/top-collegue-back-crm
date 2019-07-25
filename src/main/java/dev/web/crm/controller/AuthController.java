package dev.web.crm.controller;

import dev.web.crm.dto.CollegueAuth;
import dev.web.crm.persistence.CollegueParticipantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
