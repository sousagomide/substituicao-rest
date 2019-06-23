package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.ServidorRepository;
import br.edu.ifgoiano.substituicao.repository.filter.ServidorFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/servidores")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class ServidorResource extends AbstractResource<Servidor, ServidorFilter> {

	@Autowired
	private ServidorRepository repository;

	@Override
	protected JpaRepository<Servidor, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Servidor, ServidorFilter> getRepositoryQuery() {
		return this.repository;
	}

}
