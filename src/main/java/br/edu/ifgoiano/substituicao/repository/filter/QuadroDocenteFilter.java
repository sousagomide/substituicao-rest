package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class QuadroDocenteFilter extends BaseFilter {

	private MatrizDisciplina matrizDisciplina;

	private Servidor servidor;

	public MatrizDisciplina getMatrizDisciplina() {
		return matrizDisciplina;
	}

	public void setMatrizDisciplina(MatrizDisciplina matrizDisciplina) {
		this.matrizDisciplina = matrizDisciplina;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
