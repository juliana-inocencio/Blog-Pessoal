package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController  //tornar essa classe uma controladora de rotas - //indica que o código abaixo vai ser um controller ou um controlador da nossa API
@RequestMapping("/postagens") // falar qual a rota para essas consultas 
//("/postagens") - é o nome do caminho, endpoint  
@CrossOrigin(origins = "*", allowedHeaders = "*")
/*@CrossOrigin é usada no controlador de uma aplicação web para permitir solicitações de recursos de domínios ou 
servidores diferentes do domínio onde a aplicação está hospedada. Os atributos origins e allowedHeaders especificam as 
origens permitidas e os cabeçalhos HTTP que são permitidos nas solicitações.*/

public class PostagemController {
	
	@Autowired 
	private PostagemRepository postagemRepository;
	/*ele vai na repository e faz consulta no banco de dados 
	Está fazendo a injeção de dependencias do repository (chamando para o rolê)*/
	
	@GetMapping /*get é o verbo que vamos usar no insomnia. Só tem o get, então não tem complemento no 
	insomnia*/ 
	
	public ResponseEntity<List<Postagem>> getAll(){ //SELECT * FROM tb_postagens
		return ResponseEntity.ok(postagemRepository.findAll());
		/*A postagem tá dentro do list, pq ele cria uma collection list
		e adiciona as postagens. Se eu não colocar dentro do list ele não consegue trazer todas*/
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){ //SELECT * FROM tb_postagens where id = id;
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	

	@GetMapping("/titulo/{titulo}") //aqui tem complemento, diferente do primeiro getmapping. 
	/*Qual a diferença do que tá na chave e do que não está na chave?
	o primeiro é o texto literal postagens/titulo. É o complemento do caminho
	o segundo está entre chaves, pq ele é uma path variable, ou seja, uma variavel (espaço alocado na memoria) 
	A palavra que eu quero achar no insomnia
	*/
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){ //é a mesma coisa {titulo}
		//SELECT * FROM tb_postagens where titulo like "%titulo%";.
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); 
	/*findAllByTituloContainingIgnoreCase - vem do repository e precisa estar igual ela, é o mesmo comando
	* ResponseEntity - classe que tem como objetivo trazer o status code, 
	ele permite ver o insomnia o botão de status*/
	}		
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	@PutMapping //UPDATE tb_postagens SET titulo = "titulo", texto = "texto", data = CURRENT_TIMESTAMP() WHERE id = id;
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) //DELETE FROM tb_postagens WHERE id = id;
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		postagemRepository.deleteById(id);		
	
	}

}

