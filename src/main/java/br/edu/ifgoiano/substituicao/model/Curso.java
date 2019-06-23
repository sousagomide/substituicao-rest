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
import br.edu.ifgoiano.substituicao.model.enumeration.ModalidadeCurso;

@Entity
@Table(name = "curso")
public class Curso extends BaseModel {

	private String nome;

	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidade;

	@ManyToOne
	@JoinColumn(name = "idcampus", nullable = false)
	private Campus campus;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Turma> turmas;

	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Matriz> matrizes;

	public Curso() {
		this.ativo = true;
	}

	public Curso(Long id, String nome, ModalidadeCurso modalidade, Campus campus) {
		this.id = id;
		this.nome = nome;
		this.modalidade = modalidade;
		this.campus = campus;
		this.ativo = true;
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

	public ModalidadeCurso getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}

	public void setMatrizes(List<Matriz> matrizes) {
		this.matrizes = matrizes;
	}

}
