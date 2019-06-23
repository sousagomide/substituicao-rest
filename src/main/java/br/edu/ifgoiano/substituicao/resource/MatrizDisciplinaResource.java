package br.edu.ifgoiano.substituicao.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.pdfgen.Relatorio;
import br.edu.ifgoiano.substituicao.repository.MatrizDisciplinaRepository;
import br.edu.ifgoiano.substituicao.repository.MatrizRepository;
import br.edu.ifgoiano.substituicao.repository.filter.MatrizDisciplinaFilter;
import br.edu.ifgoiano.substituicao.repository.query.RepositoryQuery;
import br.edu.ifgoiano.substituicao.resource.abs.AbstractResource;

@RestController
@RequestMapping("/matrizesDisciplinas")
@PreAuthorize("hasAuthority('GENERIC_ROLE')")
public class MatrizDisciplinaResource extends AbstractResource<MatrizDisciplina, MatrizDisciplinaFilter> {

	@Autowired
	private MatrizDisciplinaRepository repository;
	
	@Autowired
	private MatrizRepository matrizRepository;
	
	@Override
	protected JpaRepository<MatrizDisciplina, Long> getRepository() {
		return this.repository;
	}

	@Override
	protected RepositoryQuery<MatrizDisciplina, MatrizDisciplinaFilter> getRepositoryQuery() {
		return this.repository;
	}
	
	@GetMapping("/matriz/{idMatriz}")
	public List<MatrizDisciplina> carregarPorMatriz(@PathVariable Long idMatriz) {
		return repository.findByMatriz(matrizRepository.findById(idMatriz).get());
	}
	
	@GetMapping("/relatorio")
	public void gerarRelatorio(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", System.currentTimeMillis() + "relatorio.pdf"));
			
		List<MatrizDisciplina> listaMatrizDisciplina = repository.findAll();
		String[] headers = new String[] { "Matriz (Ano/Turma)", "Disciplinas" };

		Relatorio.gerarRelatorioTabelaMatriz(response.getOutputStream(), headers, listaMatrizDisciplina);
	}

}
