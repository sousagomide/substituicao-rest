package br.edu.ifgoiano.substituicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.query.PerfilRepositoryQuery;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilRepositoryQuery {}
