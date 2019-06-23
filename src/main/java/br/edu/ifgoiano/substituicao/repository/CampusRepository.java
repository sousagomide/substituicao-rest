package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.filter.CampusFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface CampusRepository extends JpaRepository<Campus, Long>, RepositoryQuery<Campus, CampusFilter> {}
