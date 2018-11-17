package br.edu.ifgoiano.substituicao.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.repository.DisciplinaRepository;
import br.edu.ifgoiano.substituicao.repository.filter.DisciplinaFilter;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Disciplina> listar(DisciplinaFilter disciplinaFilter, Pageable pageable) {
		return repository.listar(disciplinaFilter, pageable);
	}

	@PostMapping
	public ResponseEntity<Disciplina> registrar(@RequestBody Disciplina curso, HttpServletResponse response) {
		Disciplina registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}

	@GetMapping("/{id}")
	public Disciplina listarId(@PathVariable Long id) {
		return repository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Disciplina> atualizar(@RequestBody Disciplina disciplina, @PathVariable Long id) {
		Optional<Disciplina> disciplinaOptional = repository.findById(id);
		if (!disciplinaOptional.isPresent())
			return ResponseEntity.notFound().build();
		disciplina.setId(id);
		repository.save(disciplina);
		return ResponseEntity.noContent().build();
	}

}
