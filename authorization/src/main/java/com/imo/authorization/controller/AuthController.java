package com.imo.authorization.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imo.authorization.model.RequestUserBody;
import com.imo.authorization.model.User;
import com.imo.authorization.model.ValidBody;
import com.imo.authorization.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	ObjectMapper mapper;

	@PostMapping("/login")
	public ResponseEntity loginUser(@RequestBody RequestUserBody user) throws JsonProcessingException {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

			User u = (User) authentication.getPrincipal();
			String token = jwtTokenUtil.generateToken(u);

			Map<String, String> response = new HashMap<>();

			response.put("token", token);

			String rs = mapper.writeValueAsString(response);

			return ResponseEntity.ok().header("Authorization", token).body(rs);

		} catch (BadCredentialsException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/valid/{token}")
	public boolean isValid(@PathVariable String token) {
		try {
			Date expire = jwtTokenUtil.getDateOfExpiration(token);
			
			if(expire.before(new Date())) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	
	}
}
