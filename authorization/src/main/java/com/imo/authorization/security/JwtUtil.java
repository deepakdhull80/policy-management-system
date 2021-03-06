package com.imo.authorization.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final int JWT_TOKEN_VALIDITY = 15 * 60;
	@Value("${jwt-secret}")
	private String secret;

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public Date getDateOfExpiration(String token) {
		return this.getAllClaimsFromToken(token).getExpiration();
	}

	

	public String getUsernameFromToken(String token) {
		
		return getAllClaimsFromToken(token).getSubject();
	}

	public boolean isValid(String token, UserDetails user) {
		String userName = getUsernameFromToken(token);
		
		return this.getDateOfExpiration(token).after(new Date()) && userName.equals(user.getUsername());
	}

	public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

	private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
	
	

}
