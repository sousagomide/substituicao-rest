package br.edu.ifgoiano.substituicao.resource.abs;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifgoiano.substituicao.event.RecursoCriadoEvent;
import br.edu.ifgoiano.substituicao.model.abs.BaseModel;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;

/**
 * @param <T> - Classe Model que mapeia a tabela a ser acessada no banco de dados.
 * 
 * Classe que representa um Resource genérico com todas as operações de CRUD.
 */

public abstract class AbstractResource<T extends BaseModel, F extends BaseFilter> {

	@Autowired
	protected ApplicationEventPublisher publisher;

	protected abstract JpaRepository<T, Long> getRepository();
	protected abstract RepositoryQuery<T, F> getRepositoryQuery();
	
	@GetMapping
	public Page<T> listar(F filter, Pageable pageable) {
		return getRepositoryQuery().listar(filter, pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> listarPorId(@PathVariable Long id) {
		Optional<T> response = getRepository().findById(id);
		if (!response.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(response.get());
	}
	
	@PostMapping
	public ResponseEntity<T> registrar(@RequestBody T obj, HttpServletResponse response) {
		T registro = getRepository().save(obj);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		getRepository().deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> atualizar(@RequestBody T obj, @PathVariable Long id) {
		Optional<T> objOptional = getRepository().findById(id);
		if (!objOptional.isPresent())
			return ResponseEntity.notFound().build();
		obj.setId(id);
		getRepository().save(obj);
		return ResponseEntity.noContent().build();
	}
	
}
