package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface MatrizRepository extends JpaRepository<Matriz, Long>, RepositoryQuery<Matriz, MatrizFilter> {}
