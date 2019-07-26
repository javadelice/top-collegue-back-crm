package dev.web.crm.controller;

import dev.web.crm.dto.CollegueAuth;
import dev.web.crm.dto.IdentiteCollegue;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.StatusCollegue;
import dev.web.crm.persistence.CollegueParticipantRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody CollegueAuth infos){

        Map<String, Object> infosSupplementaireToken = new HashMap<>();
        infosSupplementaireToken.put("email", infos.getEmail());

        String jetonJWT = Jwts.builder()
                .setSubject("U")
                .addClaims(infosSupplementaireToken)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                .compact();

        ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                .httpOnly(true)
                .maxAge(EXPIRES_IN * 1000)
                .path("/")
                .build();
        return collegueParticipantRepository.findByEmail(infos.getEmail())
                .filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getPassword()))
                .map(utilisateur -> ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                        .build()).orElseGet(() -> {
                    RestTemplate rt = new RestTemplate();


                    ResponseEntity<?> responseCookie = rt.postForEntity("https://robin-br-collegues-api.herokuapp.com/auth", infos, ResponseEntity.class);
                    if (responseCookie.getStatusCode().value() != 200)
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    String uriTemplate = "https://robin-br-collegues-api.herokuapp.com/me";
                    URI uri = null;
                    try {
                        uri = new URI(uriTemplate);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    String cookie = responseCookie.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);

                    RequestEntity<?> requestEntity = RequestEntity.get(uri)
                            .header(HttpHeaders.COOKIE, cookie)
                            .build();

                    ResponseEntity<IdentiteCollegue> response = rt.exchange(requestEntity, IdentiteCollegue.class);
                    IdentiteCollegue collegue = response.getBody();

                    collegueParticipantRepository.save(new CollegueParticipant(infos.getEmail(), passwordEncoder.encode(infos.getMotDePasse()), collegue.getPrenoms(), collegue.getNom(), collegue.getPhotoUrl(),StatusCollegue.SUSCRIBED));

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();
                });



    }
}
