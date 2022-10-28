package tech.brito.parkingsystem.core.configs.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tech.brito.parkingsystem.exception.ValidationJwtException;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtProvider {

    @Value("${parking.auth.jwtSecret}")
    private String jwtSecret;
    @Value("${parking.auth.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwt(Authentication authentication) {

        var expiration = new Date((new Date()).getTime() + jwtExpirationMs);
        var userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        final var roles = userPrincipal.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.joining(","));

        return Jwts
                .builder()
                .setSubject(userPrincipal.getUserId().toString())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getSubjectJwt(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public void validateJwt(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
        } catch (SignatureException | MalformedJwtException ex) {
            throw new ValidationJwtException("Authentication error: Invalid JWT signature", ex);
        } catch (ExpiredJwtException ex) {
            throw new ValidationJwtException("Authentication error: JWT token is expired", ex);
        }
    }
}
