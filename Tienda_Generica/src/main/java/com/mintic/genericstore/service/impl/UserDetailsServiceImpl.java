package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.model.User;
import com.mintic.genericstore.model.UserAuthentication;
import com.mintic.genericstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.mintic.genericstore.utils.constants.ServiceConstants.USER_DOES_NOT_EXIST;

@Service
@Slf4j
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> {
					log.error(USER_DOES_NOT_EXIST + username);
					return new UsernameNotFoundException(USER_DOES_NOT_EXIST + username);
				});
		return UserAuthentication.build(user);
	}
}
