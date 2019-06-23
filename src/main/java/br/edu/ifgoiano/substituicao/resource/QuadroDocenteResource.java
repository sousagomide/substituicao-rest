package br.edu.ifgoiano.substituicao.resource;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.model.QuadroDocente;
import br.edu.ifgoiano.substituicao.pdfgen.Relatorio;
import br.edu.ifgoiano.substituicao.repository.QuadroDocenteRepository;
import br.edu.ifgoiano.substituicao.repository.filter.QuadroDocenteFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/quadrosDocentes")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class QuadroDocenteResource extends AbstractResource<QuadroDocente, QuadroDocenteFilter> {

	@Autowired
	private QuadroDocenteRepository repository;

	@Override
	protected JpaRepository<QuadroDocente, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<QuadroDocente, QuadroDocenteFilter> getRepositoryQuery() {
		return this.repository;
	}

	@GetMapping("/relatorio")
	public void gerarRelatorio(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", System.currentTimeMillis() + "relatorio.pdf"));
			
		List<QuadroDocente> listaQuadroDocente = repository.findAll();
		String[] headers = new String[] { "Servidor", "Disciplina", "Matriz" };

		Relatorio.gerarRelatorioTabelaQuadro(response.getOutputStream(), headers, listaQuadroDocente);
	}
	
}
