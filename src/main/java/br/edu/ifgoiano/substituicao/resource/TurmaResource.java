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
import br.edu.ifgoiano.substituicao.model.Turma;
import br.edu.ifgoiano.substituicao.repository.TurmaRepository;
import br.edu.ifgoiano.substituicao.repository.filter.TurmaFilter;

@RestController
@RequestMapping("/turmas")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class TurmaResource {

	@Autowired
	private TurmaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Turma> listar(TurmaFilter turmaFilter, Pageable pageable) {
		return repository.listar(turmaFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Turma> registrar(@RequestBody Turma curso, HttpServletResponse response) {
		Turma registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> listarId(@PathVariable Long id) {
		Optional<Turma> response = repository.findById(id);
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
	public ResponseEntity<Turma> atualizar(@RequestBody Turma turma, @PathVariable Long id) {
		Optional<Turma> turmaOptional = repository.findById(id);
		if (!turmaOptional.isPresent())
			return ResponseEntity.notFound().build();
		turma.setId(id);
		repository.save(turma);
		return ResponseEntity.noContent().build();
	}
	
}
