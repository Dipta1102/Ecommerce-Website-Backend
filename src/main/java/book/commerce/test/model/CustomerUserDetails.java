package book.commerce.test.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import book.commerce.test.entity.User;

@SuppressWarnings("serial")
public class CustomerUserDetails implements UserDetails {
	private User user;

	public CustomerUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(this.user.getUserRole()));
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.user.getUserRole()));
		return authorities;
	}

	@Override
	public String getPassword() {

		return this.user.getPassword();
	}

	@Override
	public String getUsername() {

		return this.user.getUsername();
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
