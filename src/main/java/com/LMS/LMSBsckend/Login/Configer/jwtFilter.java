package com.LMS.LMSBsckend.Login.Configer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.LMS.LMSBsckend.Login.Service.JWTSservice;
import com.LMS.LMSBsckend.Login.Service.LOgService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtFilter extends OncePerRequestFilter{

	@Autowired
	JWTSservice jWTSservice;
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String authHeader=request.getHeader("Authorization");
	     String tocken=null;
	     String username=null;
	   
	     if(authHeader !=null && authHeader.startsWith("Bearer ")) {
	    	 tocken=authHeader.substring(7);
	    	 username=jWTSservice.extractUserName(tocken);
	    	  System.out.println(authHeader +" sanjay tock");
	     }
	     
	     if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
	    	 UserDetails userdetails=context.getBean(LOgService.class).loadUserByUsername(username);
	    	 try {
	    		  if(jWTSservice.validateToken(tocken,userdetails)) {
	    		 
	    			UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
	    					userdetails,null,userdetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
	    		 
	    	 }
	    	 }catch(Exception e) {
	    		 System.out.println(e);
	    			throw new ServletException(e+" Tockin Envalide");
	    	 }
	    	
	     }
	 	filterChain.doFilter(request, response);	
	}
	

}
