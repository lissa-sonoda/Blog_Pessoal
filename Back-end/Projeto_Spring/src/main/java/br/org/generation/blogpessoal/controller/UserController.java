package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.User;
import br.org.generation.blogpessoal.model.UserLogin;
import br.org.generation.blogpessoal.repository.UserRepository;
import br.org.generation.blogpessoal.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user) {
		return userService.signupUser(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> login(@RequestBody Optional<UserLogin> userlog) {
		return userService.loginUser(userlog)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> update(@Valid @RequestBody User user) {
		return userService.updateUser(user)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
