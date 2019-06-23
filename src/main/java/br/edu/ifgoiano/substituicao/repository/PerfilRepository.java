package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.filter.PerfilFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, RepositoryQuery<Perfil, PerfilFilter> {}
