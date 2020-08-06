package com.messenger.security;


import org.springframework.security.core.userdetails.UserDetails;

import com.messenger.domain.User;

public class CustomSequrityUser extends User implements UserDetails {


	private static final long serialVersionUID = -1336807584609544570L;
	
	public CustomSequrityUser(User user) {
		
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		
	}
//    @Override
//	public Set<Authority> getAuthorities() {      ////the text is redundant but may be needed in other variations of code
//		return super.getAuthorities();
//	}
//
//    @Override
//    public String getPassword() {
//    	return super.getPassword();
//    }
//    @Override
//    public String getUsername() {
//    	return super.getUsername();
//    }
   
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
