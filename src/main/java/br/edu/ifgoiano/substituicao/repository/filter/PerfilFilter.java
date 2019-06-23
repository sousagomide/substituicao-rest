package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class PerfilFilter extends BaseFilter {

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
