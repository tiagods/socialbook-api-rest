package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	@Autowired
	private LivrosRepository livros;
	
	public List<Livro> listar(){
		return livros.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro = livros.findOne(id);
		if(livro==null) {
			throw new LivroNaoEncontradoException("O livro nao pode ser encontrado");
		}		
		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livros.save(livro);
	}

	public void deletar(Long id) {
		try {
			livros.delete(id);
		}catch(EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não pode ser encontrado");
		}
	}

	public void atualizar(Livro livro) {
		verificarSeExiste(livro);
		livros.save(livro);
		
	}
	private void verificarSeExiste(Livro livro) {
		buscar(livro.getId());
	}
	
}
