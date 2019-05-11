package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Turma;
import br.edu.ifgoiano.substituicao.repository.filter.TurmaFilter;

public interface TurmaRepositoryQuery {

	public Page<Turma> listar(TurmaFilter turmaFilter, Pageable pageable);
	
}
