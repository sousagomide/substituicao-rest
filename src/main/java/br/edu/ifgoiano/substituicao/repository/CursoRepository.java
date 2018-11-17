package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.query.CursoRepositoryQuery;

public interface CursoRepository extends JpaRepository<Curso, Long>, CursoRepositoryQuery {}
