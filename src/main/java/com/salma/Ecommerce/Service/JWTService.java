package com.salma.Ecommerce.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.salma.Ecommerce.Entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuser}")
    private String issuser;
    @Value("${jwt.expiery}")
    private int experiyInSeconds;
    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct(){
        algorithm=Algorithm.HMAC256(algorithmKey);

    }

    public String generateJWT(User user){
        return JWT.create().withClaim("USERNAME",user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+(1000*experiyInSeconds))).
                withIssuer(issuser).sign(algorithm);

    }


}
