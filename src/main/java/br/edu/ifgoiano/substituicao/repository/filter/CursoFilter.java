package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.Campus;
import br.edu.ifgoiano.substituicao.model.enumeration.ModalidadeCurso;

public class CursoFilter {

	private String nome;
	private ModalidadeCurso modalidade;
	private Campus idCampus;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ModalidadeCurso getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}

	public Campus getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(Campus idCampus) {
		this.idCampus = idCampus;
	}

}
