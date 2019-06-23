package br.edu.ifgoiano.substituicao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "turma")
public class Turma extends BaseModel {
	
	private Integer ano;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	@OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Matriz> matrizes;

	public Turma() {
		this.ativo = true;
	}

	public Turma(Long id, Integer ano, Curso curso) {
		this.id = id;
		this.ano = ano;
		this.curso = curso;
		this.ativo = true;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}

	public void setMatrizes(List<Matriz> matrizes) {
		this.matrizes = matrizes;
	}

}
