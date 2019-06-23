package br.edu.ifgoiano.substituicao.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.CampusRepository;
import br.edu.ifgoiano.substituicao.repository.filter.CampusFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/campus")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class CampusResource extends AbstractResource<Campus, CampusFilter> {

	@Autowired
	private CampusRepository repository;

	@Override
	protected JpaRepository<Campus, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<Campus, CampusFilter> getRepositoryQuery() {
		return this.repository;
	}

}
