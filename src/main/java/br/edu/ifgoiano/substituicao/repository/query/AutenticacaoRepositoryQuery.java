package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.filter.AutenticacaoFilter;

public interface AutenticacaoRepositoryQuery {

	public Page<Autenticacao> listar(AutenticacaoFilter autenticacaoFilter, Pageable pageable);
	
}
