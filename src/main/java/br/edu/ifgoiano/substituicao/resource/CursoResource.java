package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.CursoRepository;
import br.edu.ifgoiano.substituicao.repository.filter.CursoFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/cursos")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class CursoResource extends AbstractResource<Curso, CursoFilter> {
	
	@Autowired
	private CursoRepository repository;

	@Override
	protected JpaRepository<Curso, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Curso, CursoFilter> getRepositoryQuery() {
		return this.repository;
	}
	
}
