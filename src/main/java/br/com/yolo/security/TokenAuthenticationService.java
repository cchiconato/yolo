package br.com.yolo.security;

import io.jsonwebtoken.MalformedJwtException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

/**
* @author  Chrisitan Chiconato
* @since   2018-02-18
*/

public class TokenAuthenticationService {

	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	private final TokenHandler tokenHandler;

	public TokenAuthenticationService(String secret, UserDetailsService userService) {
		tokenHandler = new TokenHandler(secret, userService);
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final UserDetails user = authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTH_HEADER_NAME);
		if (!StringUtils.hasText(token)) {
			token = request.getParameter(AUTH_HEADER_NAME);
		}
		if (token != null) {
			try {
				final UserDetails user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			} catch (MalformedJwtException exception) {		
				exception.printStackTrace();
				return null;
			}
		}
		return null;
	}
}