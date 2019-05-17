package br.edu.ifgoiano.substituicao.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.event.RecursoCriadoEvent;
import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.MatrizRepository;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizFilter;

@RestController
@RequestMapping("/matrizes")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class MatrizResource {
	@Autowired
	private MatrizRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Matriz> listar(MatrizFilter matrizFilter, Pageable pageable) {
		return repository.listar(matrizFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Matriz> registrar(@RequestBody Matriz curso, HttpServletResponse response) {
		Matriz registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Matriz> listarId(@PathVariable Long id) {
		Optional<Matriz> response = repository.findById(id);
		if (!response.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(response.get());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Matriz> atualizar(@RequestBody Matriz matriz, @PathVariable Long id) {
		Optional<Matriz> matrizOptional = repository.findById(id);
		if (!matrizOptional.isPresent())
			return ResponseEntity.notFound().build();
		matriz.setId(id);
		repository.save(matriz);
		return ResponseEntity.noContent().build();
	}
}
