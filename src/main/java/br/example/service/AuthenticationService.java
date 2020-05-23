package br.example.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.example.security.JWTUtil;
import br.example.security.JwtRequest;

@Service
public class AuthenticationService {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Value("${username.app.sigad.ui}")
	private String username;
	
	@Value("${password.app.sigad.ui}")
	private String password;
	
	public String createAuthenticationToken(JwtRequest req) throws AuthenticationException{
		
		if(req.getUsername().equals(username) && req.getPassword().equals(password)) {
			return jwtUtil.generateTokenApplication(req.getUsername());			
		}else {
			throw new AuthenticationException("Não foi possível criar o token");
		}
		
	}
	
}
