package br.edu.ifgoiano.substituicao.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.repository.CampusRepository;

@RestController
@RequestMapping("/campus")
public class CampusResource {
	
	@Autowired
	private CampusRepository repository;
	
	@GetMapping
	public List<Campus> listar(){
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Campus> registrar(@RequestBody Campus campus, HttpServletResponse response){
		Campus registro = repository.save(campus);
		//Cria e adiciona um Location no HttpServletResponse. Usado quando um novo recurso é criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(registro.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		//Retorna o Campus registrado
		return ResponseEntity.created(uri).body(registro);
	}
	
	@GetMapping("/{id}")
	public Campus listarId(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	
}
