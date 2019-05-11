package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Turma;
import br.edu.ifgoiano.substituicao.repository.query.TurmaRepositoryQuery;

public interface TurmaRepository extends JpaRepository<Turma, Long>, TurmaRepositoryQuery {}
