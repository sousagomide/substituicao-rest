package br.edu.ifgoiano.substituicao.repository.filter;

import java.math.BigDecimal;

public class DisciplinaFilter {

	private String nome;
	private BigDecimal cargaHoraria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(BigDecimal cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

}
