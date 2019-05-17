package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizDisciplinaFilter;

public interface MatrizDisciplinaRepositoryQuery {

	public Page<MatrizDisciplina> listar(MatrizDisciplinaFilter matrizDisciplinaFilter, Pageable pageable);
	
}
