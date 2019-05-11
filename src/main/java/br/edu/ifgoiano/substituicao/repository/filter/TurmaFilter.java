package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.Curso;

public class TurmaFilter {

	private String nome;
	private Curso curso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
