package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_topic")
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idtopic;
	
	@NotBlank(message = "The description is required")
	@Size(min = 2, max = 300, message = "The description must contain between 5 and 300 characters")
	private String description;
	
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("topic")
	private List<Post> post;

	public long getIdtopic() {
		return idtopic;
	}

	public void setIdtopic(long idtopic) {
		this.idtopic = idtopic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}
	
}
