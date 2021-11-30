package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	public List<Post> findAllByTitleContainingIgnoreCase (String title);

}
