package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.query.MatrizRepositoryQuery;

public interface MatrizRepository extends JpaRepository<Matriz, Long>, MatrizRepositoryQuery {}
