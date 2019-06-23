package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Turma;
import br.edu.ifgoiano.substituicao.repository.TurmaRepository;
import br.edu.ifgoiano.substituicao.repository.filter.TurmaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/turmas")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class TurmaResource extends AbstractResource<Turma, TurmaFilter> {

	@Autowired
	private TurmaRepository repository;

	@Override
	protected JpaRepository<Turma, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Turma, TurmaFilter> getRepositoryQuery() {
		return this.repository;
	}

}
