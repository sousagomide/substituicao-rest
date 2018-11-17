package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.filter.CursoFilter;

public interface CursoRepositoryQuery {

	public Page<Curso> listar(CursoFilter cursoFilter, Pageable pageable);
	
}
