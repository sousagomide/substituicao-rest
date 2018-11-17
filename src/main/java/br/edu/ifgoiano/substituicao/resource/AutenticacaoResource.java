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
import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.AutenticacaoRepository;
import br.edu.ifgoiano.substituicao.repository.filter.AutenticacaoFilter;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoResource {

	@Autowired
	private AutenticacaoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Autenticacao> listar(AutenticacaoFilter autenticacaoFilter, Pageable pageable) {
		return repository.listar(autenticacaoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Autenticacao> registrar(@RequestBody Autenticacao curso, HttpServletResponse response) {
		Autenticacao registro = repository.save(curso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, registro.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@GetMapping("/{id}")
	public Autenticacao listarId(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Autenticacao> atualizar(@RequestBody Autenticacao autenticacao, @PathVariable Long id) {
		Optional<Autenticacao> autenticacaoOptional = repository.findById(id);
		if (!autenticacaoOptional.isPresent())
			return ResponseEntity.notFound().build();
		autenticacao.setId(id);
		repository.save(autenticacao);
		return ResponseEntity.noContent().build();
	}
	
}
