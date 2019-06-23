package br.edu.ifgoiano.substituicao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizDisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface MatrizDisciplinaRepository extends JpaRepository<MatrizDisciplina, Long>, RepositoryQuery<MatrizDisciplina, MatrizDisciplinaFilter> {
	
	public List<MatrizDisciplina> findByMatriz(Matriz matriz);
	
}
