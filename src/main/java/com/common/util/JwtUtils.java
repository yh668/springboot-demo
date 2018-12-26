package com.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

	private static JwtBuilder createJwt(String secretkey) throws UnsupportedEncodingException {
		String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretkey.getBytes("utf-8"));
		JwtBuilder jwt = Jwts.builder().setHeaderParam("alg", "HS512").setHeaderParam("typ", "JWT");
		jwt.signWith(SignatureAlgorithm.HS512, base64EncodedSecretKey);

		return jwt;
	}

	private static Date createExpiration(Integer exphour) {
		return new Date(System.currentTimeMillis() + 60 * 60 * 1000 * exphour);
	}

	public static String generateToken(String secretkey, Integer exphour, Map<String, Object> claims)
			throws UnsupportedEncodingException {
		JwtBuilder jwt = createJwt(secretkey);

		for (Map.Entry<String, Object> entry : claims.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			jwt.claim(key, value);
		}
		jwt.setExpiration(createExpiration(exphour));
		return jwt.compact();
	}

	public static String generateToken(String secretkey, Integer exphour, Claims claims)
			throws UnsupportedEncodingException {
		JwtBuilder jwt = createJwt(secretkey);
		return jwt.setClaims(claims).setExpiration(createExpiration(exphour)).compact();

	}

	public static Boolean isTokenExpired(String token, String secretkey) {
		try {
			Claims claims = getClaimsFromToken(token, secretkey);
			Date expiration = claims.getExpiration();
			return expiration.before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	public static Claims getClaimsFromToken(String token, String secretkey) {
		Claims claims;
		try {
			String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretkey.getBytes("utf-8"));
			claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

}
