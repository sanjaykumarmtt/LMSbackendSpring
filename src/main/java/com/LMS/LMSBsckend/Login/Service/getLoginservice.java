package com.LMS.LMSBsckend.Login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.LMS.LMSBsckend.Exception.HostelException;
import com.LMS.LMSBsckend.Login.Entity.LOginDto;

@Service
public class getLoginservice {

	@Autowired
	AuthenticationManager authmanager;
	@Autowired
	JWTSservice JWTSservice;
	
	public String varicati(LOginDto LOginDto)throws HostelException{
		try {
			Authentication authen=authmanager.authenticate(
					new UsernamePasswordAuthenticationToken(LOginDto.getUsername(),
							LOginDto.getPassword()));
			
			if(authen.isAuthenticated()) {
				return JWTSservice.getTocken(LOginDto.getUsername(),authen.getAuthorities());	
			}
		}catch(Exception e) {	
			throw new HostelException(e +"Erorr");
		}
		return null;
		
	
			
	}
}
