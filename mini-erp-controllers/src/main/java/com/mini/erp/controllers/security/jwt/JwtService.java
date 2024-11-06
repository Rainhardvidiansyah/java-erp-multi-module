package com.mini.erp.controllers.security.jwt;


import com.mini.erp.controllers.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    /*

jwt:
  token:
    access: 5c4ea1e20f0df398fd261657379e6e431a7dc7044624fbd55c678e8633b894a8
    expiration: 30000
  refresh-token:
    access: new-refresh-token
    expiration: 864000000
     */

    @Value("${jwt.token.access}")
    private String tokenSecret;

    @Value("${jwt.token.expiration}")
    private Long expirationTime;

    public String generateJwtToken(Map<String, Object> data, UserDetailsImpl userDetails, Long expiration){
        String jwt = Jwts.builder()
                .claims(data)
                .subject(userDetails.getUsername())
                .expiration(new Date())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey()) // it should be signingKey
                .compact();
        return jwt;
    }



    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSecret));



    private Jws<Claims> extractAllClaims(String token){
        Jws<Claims> jws;

                jws = Jwts.parser()
                       .verifyWith(key)
                       .build()
                       .parseSignedClaims(token);

        return jws;
    }


    private Key getSigningKey(){
        byte[] signingKey = Decoders.BASE64URL.decode(this.tokenSecret);
        return Keys.hmacShaKeyFor(signingKey);
    }

    SecretKey getSigningKey2() {
        return Jwts.SIG.HS256.key().build();
    }

    SecretKey getSigningKey3() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
