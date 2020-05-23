package br.example.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private String urlAuthentication;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, String urlAuthentication) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.urlAuthentication = urlAuthentication;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader("Authorization");
		HttpServletRequest req = (HttpServletRequest) request;

		try {
			if (header != null && header.startsWith("Bearer ")) {
				if (!jwtUtil.tokenValido(header.substring(7))) {

					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "REST signature failed validation.");
					return;
				}
			} else if(!req.getRequestURI().equals(this.urlAuthentication)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "REST signature failed validation.");
				return;
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"The REST Security Server experienced an internal error.");
			return;
		}

		chain.doFilter(request, response);
	}
}