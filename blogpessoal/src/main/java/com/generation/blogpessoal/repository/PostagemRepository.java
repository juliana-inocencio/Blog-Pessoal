package com.generation.blogpessoal.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.blogpessoal.model.Postagem;
import org.springframework.data.repository.query.Param;

public interface PostagemRepository extends JpaRepository<Postagem,Long>{
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("Titulo")String titulo);
	
	/* 
	JPA - SELECT * FROM tb_postagens WHERE titulo like "algumacoisa" -  O JPA faz isso
	-- find 🡪 faz papel do select
	-- By 🡪 where
	-- Titulo 🡪 coluna que vamos fazer a busca - atributo da classe postagem
	-- Containg 🡪 like %titulo%
	-- IgnoreCase 🡪 Ignorando letras maiúsculas ou minúsculas
	-- @Param("titulo") 🡪 Define a variável String titulo como um parâmetro da consulta. 
	Esta anotação é obrigatório em consultas do tipo Like.
	-- String titulo 🡪 Parâmetro do Método contendo o título que você deseja procurar.*/
	/*Consultar mais opções aqui https://github.com/rafaelq80/cookbook_spring_v3/blob/main/03_spring/guia_jpa.md*/
	
}
