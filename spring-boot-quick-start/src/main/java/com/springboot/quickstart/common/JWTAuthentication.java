package com.springboot.quickstart.common;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springboot.quickstart.exceptions.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthentication {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthentication.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public String getToken(Long userId) {
		String jwt = null;
		try {
			long time = System.currentTimeMillis();
			jwt = Jwts.builder().setSubject("users/TzMUocMF4p").setExpiration(new Date(time + time / 3))
					.claim("user_id", userId).signWith(SignatureAlgorithm.HS256, "praveenSecretKey".getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jwt;

	}

	public Long verifyToken(String authToken) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey("praveenSecretKey".getBytes("UTF-8")).parseClaimsJws(authToken);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		} catch (MalformedJwtException e) {
			System.out.println("iam excep");
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		} catch (SignatureException e) {
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		}
		if (claims == null) {
			throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
		}
		String id;
		id = (String) claims.getBody().get("user_id").toString();
		Long userId = Long.valueOf(id);
		return userId;
	}
}
