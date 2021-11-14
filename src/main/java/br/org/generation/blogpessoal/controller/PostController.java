package br.org.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Post;
import br.org.generation.blogpessoal.repository.PostRepository;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts(){
		return ResponseEntity.ok(postRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getByIdPost(@PathVariable long id){
		return postRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Post>> getByTitlePost(@PathVariable String title){
		return ResponseEntity.ok(postRepository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping
	public ResponseEntity<Post> postPost(@Valid @RequestBody Post post){
		return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));
	}
	
	@PutMapping
	public ResponseEntity<Post> putPost(@Valid @RequestBody Post post){
		return postRepository.findById(post.getIdpost())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(postRepository.save(post)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable long id) {
		return postRepository.findById(id)
				.map(resp -> {
					postRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
