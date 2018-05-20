package com.combishackaton.app.security.auth.jwt;

import com.combishackaton.app.security.auth.model.Token;
import com.combishackaton.app.security.auth.model.TokenType;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenFactory {

    public static final String ACCESS_TOKEN_BODY_TAG = "body";
    public static final String TOKEN_TYPE = "token_type";

    @Value("${jwt.secret:}")
    private String secret;

    @Value("${jwt.access.expiration-time:6048000}")
    private Long accessExpiration;

    @Value("${jwt.refresh.expiration-time:315569260}")
    private Long refreshExpiration;

    public Token generate(UserPrincipal userPrincipal, Boolean refreshToken) {
        return new Token().setAccessToken(generateAccessToken(userPrincipal))
                          .setRefreshToken(refreshToken ? generateRefreshToken(userPrincipal.getId()) : null);
    }

    private String generateAccessToken(UserPrincipal userPrincipal) {
        Claims builder = Jwts.claims();
        builder.put(TOKEN_TYPE, TokenType.ACCESS_TOKEN);
        builder.put(ACCESS_TOKEN_BODY_TAG, userPrincipal);
        builder.setSubject(userPrincipal.getId());
        builder.setExpiration(generateExpirationDate(accessExpiration));
        return build(builder);
    }

    private String generateRefreshToken(String userId) {
        Claims builder = Jwts.claims();
        builder.put(TOKEN_TYPE, TokenType.REFRESH_TOKEN);
        builder.setId(UUID.randomUUID().toString())
               .setSubject(userId)
               .setExpiration(generateExpirationDate(refreshExpiration));
        return build(builder);
    }

    private String build(Claims claims) {
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date())
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    private Date generateExpirationDate(Long expirationTime) {
        return new Date(System.currentTimeMillis() + (expirationTime * 1000));
    }
}
