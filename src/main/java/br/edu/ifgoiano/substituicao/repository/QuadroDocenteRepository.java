package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.QuadroDocente;
import br.edu.ifgoiano.substituicao.repository.filter.QuadroDocenteFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface QuadroDocenteRepository extends JpaRepository<QuadroDocente, Long>, RepositoryQuery<QuadroDocente, QuadroDocenteFilter> {}
