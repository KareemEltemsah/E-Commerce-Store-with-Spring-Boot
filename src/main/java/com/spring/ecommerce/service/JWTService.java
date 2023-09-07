package com.spring.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.ecommerce.model.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String algorithmKey = "SuperSecureSecretKey";
    private static final String issuer = "eCommerce";
    private static final Long expiryInSeconds = 604800L;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJwtToken(LocalUser user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserName(String token) {
        return JWT.decode(token).getSubject();
    }
}
