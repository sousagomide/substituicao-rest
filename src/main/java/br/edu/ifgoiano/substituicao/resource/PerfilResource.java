package br.edu.ifgoiano.substituicao.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.PerfilRepository;
import br.edu.ifgoiano.substituicao.repository.filter.PerfilFilter;

@RestController
@RequestMapping("/perfis")
public class PerfilResource {

	@Autowired
	private PerfilRepository repository;
	
	@GetMapping
	public Page<Perfil> listar(PerfilFilter perfilFilter, Pageable pageable) {
		return repository.listar(perfilFilter, pageable);
	}
	
}
