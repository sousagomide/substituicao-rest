package br.edu.ifgoiano.substituicao.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "autenticacao")
public class Autenticacao extends BaseModel {

	private String usuario;

	private String senha;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "idperfil", nullable = false)
	private Perfil perfil;

	public Autenticacao() {
		this.ativo = true;
	}
	
	public Autenticacao(Long id, String usuario, String senha, boolean ativo, Perfil perfil) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.ativo = ativo;
		this.perfil = perfil;
		this.ativo = true;
	}

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

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Perfil getIdPerfil() {
		return perfil;
	}

	public void setIdPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
