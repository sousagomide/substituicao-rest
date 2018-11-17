package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.repository.filter.DisciplinaFilter;

public interface DisciplinaRepositoryQuery {

	public Page<Disciplina> listar(DisciplinaFilter disciplinaFilter, Pageable pageable);
	
}
