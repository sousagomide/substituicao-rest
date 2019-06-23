package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.model.enumeration.CategoriaServidor;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class ServidorFilter extends BaseFilter {

	private String nome;
	private String siape;
	private CategoriaServidor categoria;
	private String funcao;
	private String email;
	private Autenticacao idAutenticacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public CategoriaServidor getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaServidor categoria) {
		this.categoria = categoria;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Autenticacao getIdAutenticacao() {
		return idAutenticacao;
	}

	public void setIdAutenticacao(Autenticacao idAutenticacao) {
		this.idAutenticacao = idAutenticacao;
	}

}
