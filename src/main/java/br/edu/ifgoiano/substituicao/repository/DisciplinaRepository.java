package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.repository.filter.DisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>, RepositoryQuery<Disciplina, DisciplinaFilter> {}
