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
import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.ServidorRepository;
import br.edu.ifgoiano.substituicao.repository.filter.ServidorFilter;

@RestController
@RequestMapping("/servidores")
public class ServidorResource {

	@Autowired
	private ServidorRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Servidor> listar(ServidorFilter servidorFilter, Pageable pageable) {
		return repository.listar(servidorFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Servidor> registrar(@RequestBody Servidor curso, HttpServletResponse response) {
		Servidor registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servidor> listarId(@PathVariable Long id) {
		Optional<Servidor> response = repository.findById(id);
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
	public ResponseEntity<Servidor> atualizar(@RequestBody Servidor servidor, @PathVariable Long id) {
		Optional<Servidor> servidorOptional = repository.findById(id);
		if (!servidorOptional.isPresent())
			return ResponseEntity.notFound().build();
		servidor.setId(id);
		repository.save(servidor);
		return ResponseEntity.noContent().build();
	}
	
}
