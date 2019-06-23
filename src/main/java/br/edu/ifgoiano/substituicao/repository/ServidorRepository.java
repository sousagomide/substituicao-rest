package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.filter.ServidorFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface ServidorRepository extends JpaRepository<Servidor, Long>, RepositoryQuery<Servidor, ServidorFilter> {}
