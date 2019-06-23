package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class CampusFilter extends BaseFilter {

	private String nome;
	private String instituicao;
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
