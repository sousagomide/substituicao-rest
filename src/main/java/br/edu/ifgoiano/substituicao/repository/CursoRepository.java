package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.filter.CursoFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface CursoRepository extends JpaRepository<Curso, Long>, RepositoryQuery<Curso, CursoFilter> {}
