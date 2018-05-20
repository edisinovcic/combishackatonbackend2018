package com.combishackaton.app.security.auth.jwt;

import com.combishackaton.app.security.auth.model.TokenType;
import com.combishackaton.app.security.auth.model.UserPrincipal;
import com.combishackaton.app.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenUtils {

    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${jwt.secret:}")
    private String secret;

    public Boolean validateAccessToken(String token) {
        try {
            Claims claims = getClaims(token);
            return TokenType.ACCESS_TOKEN.equals(getTokenType(claims)) && claims.getExpiration().after(new Date());
        } catch(Exception ex) {
            return false;
        }
    }

    public Boolean validateRefreshToken(String token, User user) {
        try {
            Claims claims = getClaims(token);
            //Date passwordChangeDate = Date.from(user.getChangePasswordTime().withNano(0).atZone(ZoneId.systemDefault()).toInstant());

            return TokenType.REFRESH_TOKEN.equals(getTokenType(claims));
            /*
             && claims.getExpiration()
                                                                                 .after(new Date()) && claims
                    .getSubject().equals(user.getId()) && (claims.getIssuedAt().equals(passwordChangeDate) || claims
                    .getIssuedAt().after(passwordChangeDate));
                    */
        } catch(Exception ex) {
            return false;
        }
    }

    public UserPrincipal getUserPrincipal(String token) {
        return mapper.convertValue(getClaims(token).get(JwtTokenFactory.ACCESS_TOKEN_BODY_TAG), UserPrincipal.class);
    }

    private TokenType getTokenType(Claims claims) {
        return TokenType.valueOf(claims.get(JwtTokenFactory.TOKEN_TYPE).toString());
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
