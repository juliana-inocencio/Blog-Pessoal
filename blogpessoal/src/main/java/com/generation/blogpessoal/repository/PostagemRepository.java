package com.generation.blogpessoal.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.blogpessoal.model.Postagem;
import org.springframework.data.repository.query.Param;

public interface PostagemRepository extends JpaRepository<Postagem,Long>{
	// JPA - SELECT * FROM tb_postagens WHERE titulo like "algumacoisa" ---  O JPA faz isso
	// find faz papel do select
	//by = where
	// titulo = coluna que vamos fazer a busca
	//JPA faz uma junção do sql com repository
	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("Titulo")String titulo);
	
	
	
}
