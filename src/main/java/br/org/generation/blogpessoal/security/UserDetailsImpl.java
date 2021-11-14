package br.org.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.blogpessoal.model.User;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String usernameDet;
	private String passwordDet;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.usernameDet = user.getUsername();
		this.passwordDet = user.getPassword();		
	}

	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return passwordDet;
	}

	@Override
	public String getUsername() {

		return usernameDet;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
