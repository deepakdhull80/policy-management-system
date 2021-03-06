package com.imo.authorization.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imo.authorization.repository.UserRepository;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		if(header == null || ! header.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.split(" ")[1].trim();
		
		
		String userName = jwtUtil.getUsernameFromToken(token);
		
		UserDetails user = userRepository.findById(userName).get();
		
		if(!jwtUtil.isValid(token,user)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user==null?List.of():user.getAuthorities());
		
		auth.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request)
				);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
		
		
	}

}
