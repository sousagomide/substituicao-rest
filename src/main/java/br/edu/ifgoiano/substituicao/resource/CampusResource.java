package br.edu.ifgoiano.substituicao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
