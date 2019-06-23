package br.edu.ifgoiano.substituicao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.filter.AutenticacaoFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long>, RepositoryQuery<Autenticacao, AutenticacaoFilter> {
	
	public Optional<Autenticacao> findByUsuario(String username);
	
}
