package br.org.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.User;
import br.org.generation.blogpessoal.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameDet) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUsername(usernameDet);
		user.orElseThrow(() -> new UsernameNotFoundException(usernameDet + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}

}
