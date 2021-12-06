package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long iduser;
	
	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 200, message = "The name must contain between 1 and 200 characters")
	private String name;
	
	@ApiModelProperty(example = "email@email.com.br")
	@NotNull(message = "The username is required")
	@Email(message = "The username must be a valid e-mail")
	private String username;
	
	@NotNull(message = "The password is required")
	@Size(min = 8, message = "The password must contain at least 8 characters")
	private String password;
	
	private String picture;
	
	private String usertype;
	
	@OneToMany(mappedBy = "username", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("username")
	private List<Post> posts;

	public User(long iduser, String name, String username, String password) {
		this.iduser = iduser;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public User() {}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
