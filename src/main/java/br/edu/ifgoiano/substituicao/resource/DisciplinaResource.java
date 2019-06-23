package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.repository.DisciplinaRepository;
import br.edu.ifgoiano.substituicao.repository.filter.DisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/disciplinas")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class DisciplinaResource extends AbstractResource<Disciplina, DisciplinaFilter> {

	@Autowired
	private DisciplinaRepository repository;

	@Override
	protected JpaRepository<Disciplina, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Disciplina, DisciplinaFilter> getRepositoryQuery() {
		return this.repository;
	}

}
