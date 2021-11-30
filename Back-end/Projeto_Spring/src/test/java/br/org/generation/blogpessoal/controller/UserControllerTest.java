package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.User;
import br.org.generation.blogpessoal.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UserService usuarioService;

	@Test
	@Order(1)
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {

		HttpEntity<User> requisicao = new HttpEntity<User>(new User(0L, 
			"Paulo Antunes", "paulo_antunes@email.com.br", "13465278"));

		ResponseEntity<User> resposta = testRestTemplate
			.exchange("/users/signup", HttpMethod.POST, requisicao, User.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getName(), resposta.getBody().getName());
		assertEquals(requisicao.getBody().getUsername(), resposta.getBody().getUsername());
	}

	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.signupUser(new User(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278"));

		HttpEntity<User> requisicao = new HttpEntity<User>(new User(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278"));

		ResponseEntity<User> resposta = testRestTemplate
			.exchange("/users/signup", HttpMethod.POST, requisicao, User.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Alterar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<User> usuarioCreate = usuarioService.signupUser(new User(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123"));

		User usuarioUpdate = new User(usuarioCreate.get().getIduser(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123");
		
		HttpEntity<User> requisicao = new HttpEntity<User>(usuarioUpdate);

		ResponseEntity<User> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/users/update", HttpMethod.PUT, requisicao, User.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getName(), resposta.getBody().getName());
		assertEquals(usuarioUpdate.getUsername(), resposta.getBody().getUsername());
	}

	@Test
	@Order(4)
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.signupUser(new User(0L, 
			"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123"));
		
		usuarioService.signupUser(new User(0L, 
			"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123"));

		ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/users/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
