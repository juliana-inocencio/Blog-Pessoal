package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_temas")
public class Tema {
	
	@Id //definir que a partir daqui é um id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //gerar o valor sozinho,como se fosse o auto_increment
	private Long id; //id do tipo Long

	//não permite que o Atributo seja Nulo, mas permite que ele contenha apenas Espaços em branco
	@NotNull(message = "O Atributo Descrição é obrigatório") //obrigar o usuário a colocar o nome = notnull
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="tema",cascade = CascadeType.REMOVE)
	/* 1 tema para muitas postagens 
	-- fetch 🡪 FetchType.LAZY = pede para que pegue tudo de forma lenta
	-- mappedBy="tema" 🡪 é só pra One e não pro Many da relação
	-- cascade = CascadeType.REMOVE 🡪 remove o tema, então remove todas as postagens desse tema junto
	*/
	
	@JsonIgnoreProperties("tema") //Vai aparecer só o que eu pedi, se não colocar vou acabar exibindo tema-postagem-postagem-tema...um vai puxar o outro
	
	private List<Postagem> postagem; // é só pra One e não pro Many da relação
	
	
	//getters and setters, não esqueça de fazer os da relação em seguida

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
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