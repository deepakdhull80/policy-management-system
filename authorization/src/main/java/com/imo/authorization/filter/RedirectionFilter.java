package com.imo.authorization.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.imo.authorization.security.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


@Configuration
public class RedirectionFilter extends ZuulFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		String header = request.getHeader("Authorization");
//		System.out.println(header);
		String username = jwtUtil.getUsernameFromToken(header.split(" ")[1]);
		System.out.println("-------- setting user UID header ----------------");
		ctx.put("username", username);
		ctx.addZuulRequestHeader("username", username);

		return null;
	}

}
