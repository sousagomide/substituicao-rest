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
@Table(name = "matriz")
public class Matriz extends BaseModel {

	private Integer ano;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "idturma")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	@OneToMany(mappedBy = "matriz", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<MatrizDisciplina> matrizesDisciplinas;

	public Matriz() {
		this.ativo = true;
	}

	public Matriz(Long id, Integer ano, Turma turma, Curso curso) {
		this.id = id;
		this.ano = ano;
		this.turma = turma;
		this.curso = curso;
		this.ativo = true;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<MatrizDisciplina> getMatrizesDisciplinas() {
		return matrizesDisciplinas;
	}

	public void setMatrizesDisciplinas(List<MatrizDisciplina> matrizesDisciplinas) {
		this.matrizesDisciplinas = matrizesDisciplinas;
	}

}
