package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizFilter;

public interface MatrizRepositoryQuery {

	public Page<Matriz> listar(MatrizFilter matrizFilter, Pageable pageable);
	
}
