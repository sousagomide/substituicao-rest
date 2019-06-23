package br.edu.ifgoiano.substituicao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "campus")
public class Campus extends BaseModel {

	private String instituicao;

	private String nome;

	private Boolean ativo;

	@OneToMany(mappedBy = "campus", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Curso> cursos;

	public Campus() {
		this.ativo = true;
	}

	public Campus(Long id, String nome, String instituicao) {
		this.id = id;
		this.nome = nome;
		this.instituicao = instituicao;
		this.ativo = true;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
