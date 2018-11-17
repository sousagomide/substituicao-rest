package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.filter.PerfilFilter;

public interface PerfilRepositoryQuery {

	public Page<Perfil> listar(PerfilFilter perfilFilter, Pageable pageable);
	
}
