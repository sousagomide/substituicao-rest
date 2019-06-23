package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.AutenticacaoRepository;
import br.edu.ifgoiano.substituicao.repository.filter.AutenticacaoFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoResource extends AbstractResource<Autenticacao, AutenticacaoFilter> {

	@Autowired
	private AutenticacaoRepository repository;

	@Override
	protected JpaRepository<Autenticacao, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Autenticacao, AutenticacaoFilter> getRepositoryQuery() {
		return this.repository;
	}

}
