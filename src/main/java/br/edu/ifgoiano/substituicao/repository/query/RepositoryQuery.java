package br.edu.ifgoiano.substituicao.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public interface RepositoryQuery<T extends BaseModel, F extends BaseFilter> {
	
	public Page<T> listar(F filter, Pageable pageable);

}
