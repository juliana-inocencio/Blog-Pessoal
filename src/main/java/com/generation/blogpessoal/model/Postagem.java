package com.generation.blogpessoal.model;
//Model: modelagem do banco de dados(criação das tabelas)

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //referencia que é uma tabela
@Table(name = "tb_postagens") //define o nome da tabela
public class Postagem {

	@Id //definir que a partir daqui é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar o valor sozinho,como se fosse o auto_increment
    private Long id; //id do tipo Long

    @NotBlank(message = "O atributo título é Obrigatório!") //obrigar o usuário a colocar o nome = notnull
    @Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
    /*A anotação @Size define o valor Mínimo (min) e o valor Máximo (max) de caracteres do Atributo. Não é obrigatório
    configurar os 2 parâmetros. Como o parâmetro max foi configurado, observe que o mesmo valor informado será inserido 
    na definição dos Atributos titulo (varchar(100)) e texto (varchar(1000)) na tabela tb_postagens no Banco de dados. 
    Você pode configurar uma mensagem para o usuário através do Atributo message.*/
    private String titulo;

    @NotBlank(message = "O atributo texto é Obrigatório!")
    @Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres")
    private String texto;

    @UpdateTimestamp //AUTOMATIZA A DATA DA APLICAÇÃO PARA A DATA DO SISTEMA
    private LocalDateTime data;
    
    @ManyToOne
    @JsonIgnoreProperties("postagem") // importante pra não exibir várias vezes a mesma informação, colocar o local onde se está
    private Tema tema; // não precisaria chamar, pois estão no mesmo pacote, já são da família
    
    /* = FOREIGN KEY (TEMA_ID) REFERENCE TB_TEMA.ID
    	-- @ManyToOne 🡪 (da relação de que se trata, levando em consideração da atual para a outra)
    	-- @JsonIgnoreProperties("nomeDaClassAtual")
    	-- Colocar Getters and Setters da Tema.
    	-- FAZER DE TEMA PARA POSTAGEM TAMBÉM.
     */
    
    @ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
	//Tema
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	//Usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
