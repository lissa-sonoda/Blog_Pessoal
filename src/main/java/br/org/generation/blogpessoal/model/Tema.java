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
@Table(name = "tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idtema;
	
	@NotBlank(message = "A descrição é obrigatória")
	@Size(min = 2, max = 100, message = "A descrição deve conter entre 5 a 100 caracteres")
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	
	public long getIdtema() {
		return idtema;
	}

	public void setIdtema(long idtema) {
		this.idtema = idtema;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Postagem> getPostagem() {
		return postagem;
	}
	
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}
