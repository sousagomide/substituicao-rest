package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.Disciplina;
import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class MatrizDisciplinaFilter extends BaseFilter {

	private Matriz matriz;
	private Disciplina disciplina;

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
