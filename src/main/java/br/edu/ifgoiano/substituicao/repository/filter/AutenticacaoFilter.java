package br.edu.ifgoiano.substituicao.repository.filter;

import br.edu.ifgoiano.substituicao.model.Perfil;
import br.edu.ifgoiano.substituicao.repository.filter.abs.BaseFilter;

public class AutenticacaoFilter extends BaseFilter {

	private String usuario;
	private String senha;
	private Perfil idPerfil;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Perfil idPerfil) {
		this.idPerfil = idPerfil;
	}

}
