package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.MatrizRepository;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/matrizes")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class MatrizResource extends AbstractResource<Matriz, MatrizFilter> {

	@Autowired
	private MatrizRepository repository;

	@Override
	protected JpaRepository<Matriz, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Matriz, MatrizFilter> getRepositoryQuery() {
		return this.repository;
	}

}
