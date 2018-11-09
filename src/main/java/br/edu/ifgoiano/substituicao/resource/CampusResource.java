package br.edu.ifgoiano.substituicao.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.CampusRepository;

@RestController
@RequestMapping("/campus")
public class CampusResource {
	
	@Autowired
	private CampusRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Campus> listar(){
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Campus> registrar(@RequestBody Campus campus, HttpServletResponse response){
		Campus registro = repository.save(campus);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@GetMapping("/{id}")
	public Campus listarId(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Campus> atualizar(@RequestBody Campus campus, @PathVariable Long id) {
		Optional<Campus> campusOptional = repository.findById(id);
		if (!campusOptional.isPresent())
			return ResponseEntity.notFound().build();
		campus.setId(id);
		repository.save(campus);
		return ResponseEntity.noContent().build();
	}
	
}
