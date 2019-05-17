package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.repository.query.MatrizDisciplinaRepositoryQuery;

public interface MatrizDisciplinaRepository extends JpaRepository<MatrizDisciplina, Long>, MatrizDisciplinaRepositoryQuery {}
