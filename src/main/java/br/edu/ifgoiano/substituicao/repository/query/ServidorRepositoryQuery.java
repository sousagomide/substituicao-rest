package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.filter.ServidorFilter;

public interface ServidorRepositoryQuery {

	public Page<Servidor> listar(ServidorFilter servidorFilter, Pageable pageable);
	
}
