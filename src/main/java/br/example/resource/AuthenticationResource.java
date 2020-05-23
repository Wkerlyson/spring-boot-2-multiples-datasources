package br.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.example.security.JwtRequest;
import br.example.service.AuthenticationService;

@RestController
public class AuthenticationResource {
		
	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/authentication")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest req) throws Exception {
		
		String token = service.createAuthenticationToken(req);
			    
		return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(null);

	}
}
