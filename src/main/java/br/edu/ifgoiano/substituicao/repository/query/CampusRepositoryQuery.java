package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.filter.CampusFilter;

public interface CampusRepositoryQuery {
	
	public Page<Campus> listar(CampusFilter campusFilter, Pageable pageable);

}
