package br.edu.ifgoiano.substituicao.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifgoiano.substituicao.model.Curso;
import br.edu.ifgoiano.substituicao.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoResource {
	
	@Autowired
	private CursoRepository repository;
	
	@GetMapping
	public List<Curso> listar() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Curso> registrar(@RequestBody Curso curso, HttpServletResponse response) {
		Curso registro = repository.save(curso);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(registro.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(registro);
	}
	
	@GetMapping("/{id}")
	public Curso listarId(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
}
