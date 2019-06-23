package br.edu.ifgoiano.substituicao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;
import br.edu.ifgoiano.substituicao.model.enumeration.CategoriaServidor;

@Entity
@Table(name = "servidor")
public class Servidor extends BaseModel {

	private String nome;

	private String siape;

	@Enumerated(EnumType.STRING)
	private CategoriaServidor categoria;

	private String funcao;

	private String email;

	@ManyToOne
	@JoinColumn(name = "idautenticacao")
	private Autenticacao autenticacao;

	@OneToMany(mappedBy = "servidor", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<QuadroDocente> quadrosDocentes;

	public Servidor() { }
	
	public Servidor(Long id, String nome, String siape, CategoriaServidor categoria, String funcao, String email,
			Autenticacao autenticacao) {
		this.id = id;
		this.nome = nome;
		this.siape = siape;
		this.categoria = categoria;
		this.funcao = funcao;
		this.email = email;
		this.autenticacao = autenticacao;
	}

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
		return autenticacao;
	}

	public void setIdAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public List<QuadroDocente> getQuadrosDocentes() {
		return quadrosDocentes;
	}

	public void setQuadrosDocentes(List<QuadroDocente> quadrosDocentes) {
		this.quadrosDocentes = quadrosDocentes;
	}

}
