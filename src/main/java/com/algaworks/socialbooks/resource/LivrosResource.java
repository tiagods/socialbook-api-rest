package com.algaworks.socialbooks.resource;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;

@RestController
@RequestMapping(value="/livros")
public class LivrosResource {
	
	@Autowired
	private LivrosService livros;
	
	@CrossOrigin//permite que as chamadas funcionem em multiplos dominios ...javascript
	@RequestMapping(method=RequestMethod.GET,produces={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livros.listar());
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		livro  = livros.salvar(livro);
		//ao salvar um objeto informarei para o cliente onde ele vai localizar o recurso com codigo httpstatus 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Livro livro = livros.buscar(id);
		CacheControl cache = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cache).body(livro);			

	}
	@RequestMapping(value ="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		livros.deletar(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livros.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
}
