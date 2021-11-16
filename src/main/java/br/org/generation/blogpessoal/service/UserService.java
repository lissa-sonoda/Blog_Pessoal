package br.org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.User;
import br.org.generation.blogpessoal.model.UserLogin;
import br.org.generation.blogpessoal.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Optional<User> signupUser(User user) {
		
		if (userRepository.findByUsername(user.getUsername()).isPresent())
			return Optional.empty();
		
		user.setPassword(encoder(user.getPassword()));
		
		return Optional.of(userRepository.save(user));

	}

	public Optional<UserLogin> loginUser(Optional<UserLogin> userlog) {

		Optional<User> user = userRepository.findByUsername(userlog.get().getUsernamelog());

		if (user.isPresent()) {
			if (compare(userlog.get().getPasswordlog(), user.get().getPassword())) {

				String token = generateToken(userlog.get().getUsernamelog(), userlog.get().getPasswordlog());
				
				userlog.get().setIdlog(user.get().getIduser());
				userlog.get().setNamelog(user.get().getName());
				userlog.get().setPasswordlog(user.get().getPassword());
				userlog.get().setToken(token);

				return userlog;
			}
		}
		return Optional.empty();
	}
	
	public Optional<User> updateUser(User user){
		
		if (userRepository.findById(user.getIduser()).isPresent()) {
			Optional<User> searchUser = userRepository.findByUsername(user.getUsername());
			
			if(searchUser.isPresent()) {
				if(searchUser.get().getIduser() != user.getIduser())
					return Optional.empty();
			}
			
			user.setPassword(encoder(user.getPassword()));
			return Optional.of(userRepository.save(user));
		}
		return Optional.empty();
	}
	
	private String encoder(String password) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
		
	}

	private boolean compare(String passwordlog, String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(passwordlog, password);

	}

	private String generateToken(String username, String password) {

		String tokenBase = username + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
}
