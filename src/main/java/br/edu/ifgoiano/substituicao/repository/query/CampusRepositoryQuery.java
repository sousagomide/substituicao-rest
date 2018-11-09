package br.edu.ifgoiano.substituicao.repository.query;

import java.util.List;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.filter.CampusFilter;

public interface CampusRepositoryQuery {
	
	public List<Campus> listar(CampusFilter campusFilter);

}
