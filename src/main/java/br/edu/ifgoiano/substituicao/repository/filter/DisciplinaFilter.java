package br.edu.ifgoiano.substituicao.repository.filter;

import java.math.BigDecimal;

import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class DisciplinaFilter extends BaseFilter {

	private String nome;
	private BigDecimal cargaHoraria;
	private Boolean ativo;

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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
