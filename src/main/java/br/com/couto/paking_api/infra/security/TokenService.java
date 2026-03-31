package br.com.couto.paking_api.infra.security;

import br.com.couto.paking_api.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

      @Value("${api.security.token.secret}")
    private String secretKey;
      public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("parking-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExperitionDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
        throw new RuntimeException("erro while generating token",exception);
        }

    }



    public String validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
         return JWT.require(algorithm)

                 .withIssuer("parking-api")
                 .build()
                 .verify(token)
                 .getSubject();
        } catch (JWTVerificationException exception){
             return "";
        }
    }



    private Instant genExperitionDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
