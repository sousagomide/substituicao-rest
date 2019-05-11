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
import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.CursoRepository;
import br.edu.ifgoiano.substituicao.repository.filter.CursoFilter;

@RestController
@RequestMapping("/cursos")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class CursoResource {
	
	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Curso> listar(CursoFilter cursoFilter, Pageable pageable) {
		return repository.listar(cursoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Curso> registrar(@RequestBody Curso curso, HttpServletResponse response) {
		Curso registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> listarId(@PathVariable Long id) {
		Optional<Curso> response = repository.findById(id);
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
	public ResponseEntity<Curso> atualizar(@RequestBody Curso curso, @PathVariable Long id) {
		Optional<Curso> cursoOptional = repository.findById(id);
		if (!cursoOptional.isPresent())
			return ResponseEntity.notFound().build();
		curso.setId(id);
		repository.save(curso);
		return ResponseEntity.noContent().build();
	}
	
}
