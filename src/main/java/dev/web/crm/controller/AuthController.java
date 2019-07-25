package dev.web.crm.controller;
import dev.web.crm.dto.CollegueAuth;
import dev.web.crm.persistence.CollegueParticipantRepository;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (allowCredentials = "true")
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


    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody CollegueAuth infos) {

        return this.collegueParticipantRepository.findByEmail(infos.getEmail())
          .filter(collegue -> passwordEncoder.matches(infos.getPassword(), collegue.getPassword()))
          .map(collegue -> {
              
        	  /*Map<String, Object> infosSupplementaireToken = new HashMap<>();
              infosSupplementaireToken.put("status", collegue.getStatus());*/
              
              String jetonJWT = Jwts.builder()
                      .setSubject(collegue.getEmail())
                      //.addClaims(infosSupplementaireToken)
                      .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                      .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                      .compact();
              
              ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                      .httpOnly(true)
                      .maxAge(EXPIRES_IN * 1000)
                      .path("/")
                      .build();
          
          return ResponseEntity.ok()
                  .header(HttpHeaders.SET_COOKIE,  tokenCookie.toString())
                  .build();
          })
          .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

      }




}
