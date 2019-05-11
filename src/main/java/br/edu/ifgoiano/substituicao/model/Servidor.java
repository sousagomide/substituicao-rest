package br.edu.ifgoiano.substituicao.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifgoiano.substituicao.model.enumeration.CategoriaServidor;

@Entity
@Table(name = "servidor")
public class Servidor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String siape;
	
	@Enumerated(EnumType.STRING)
	private CategoriaServidor categoria;
	
	private String funcao;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "idautenticacao")
	private Autenticacao autenticacao;

	public Servidor() {}
	
	public Servidor(Long id, String nome, String siape, CategoriaServidor categoria, String funcao, String email, Autenticacao autenticacao) {
		this.id = id;
		this.nome = nome;
		this.siape = siape;
		this.categoria = categoria;
		this.funcao = funcao;
		this.email = email;
		this.autenticacao = autenticacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servidor other = (Servidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
