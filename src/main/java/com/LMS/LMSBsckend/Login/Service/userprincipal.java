package com.LMS.LMSBsckend.Login.Service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.LMS.LMSBsckend.Login.Entity.LoginEntity;

public class userprincipal implements UserDetails{

	private final LoginEntity loginEntity;
	public userprincipal(LoginEntity loginEntity) {
		this.loginEntity=loginEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  Collections.singleton(new SimpleGrantedAuthority("ROLE_"+loginEntity.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return loginEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return loginEntity.getUsername();
	}

}
