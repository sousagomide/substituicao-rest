package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.query.ServidorRepositoryQuery;

public interface ServidorRepository extends JpaRepository<Servidor, Long>, ServidorRepositoryQuery {}
