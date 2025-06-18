package com.mintic.genericstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserAuthentication implements org.springframework.security.core.userdetails.UserDetails {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private final Long idNumber;

	private final String fullName;

	private final String email;

	private final String username;

	@JsonIgnore
	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;

	public static UserAuthentication build(User user) {
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.toList();

		return UserAuthentication.builder()
				.idNumber(user.getIdNumber())
				.fullName(user.getFullName())
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.authorities(authorities)
				.build();
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
