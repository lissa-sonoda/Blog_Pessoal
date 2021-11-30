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

import br.org.generation.blogpessoal.model.Topic;
import br.org.generation.blogpessoal.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@GetMapping
	public ResponseEntity<List<Topic>> getAllTopics(){
		return ResponseEntity.ok(topicRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Topic> getByIdTopic(@PathVariable long id){
		return topicRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/desc/{description}")
	public ResponseEntity<List<Topic>> getByDescription(@PathVariable String description){
		return ResponseEntity.ok(topicRepository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	@PostMapping
	public ResponseEntity<Topic> postTopic(@Valid @RequestBody Topic topic){
		return ResponseEntity.status(HttpStatus.CREATED).body(topicRepository.save(topic));
	}
	
	@PutMapping
	public ResponseEntity<Topic> putTopic(@Valid @RequestBody Topic topic){
		return topicRepository.findById(topic.getIdtopic())
				.map(resp -> {
					return ResponseEntity.status(HttpStatus.OK).body(topicRepository.save(topic));
				})
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTopic(@PathVariable long id) {
		return topicRepository.findById(id)
				.map(resp -> {
					topicRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
