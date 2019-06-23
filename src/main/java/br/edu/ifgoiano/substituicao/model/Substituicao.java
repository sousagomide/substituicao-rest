package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "substituicao")
public class Substituicao extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "datasubstituicao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataSubstituicao;

	@Column(name = "horariosubstituicao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioSubstituicao;

	@Column(name = "datareposicao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataReposicao;

	@Column(name = "horarioreposicao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horarioReposicao;

	@ManyToOne
	@JoinColumn(name = "idturma")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "idfichasubstituicao")
	private FichaSubstituicao fichaSubstituicao;

	@ManyToOne
	@JoinColumn(name = "idsubstituto")
	private Servidor substituto;

	@ManyToOne
	@JoinColumn(name = "iddisciplinasolicitante")
	private QuadroDocente disciplinaSolicitante;

	public Substituicao() { }
	
	public Calendar getDataSubstituicao() {
		return dataSubstituicao;
	}

	public void setDataSubstituicao(Calendar dataSubstituicao) {
		this.dataSubstituicao = dataSubstituicao;
	}

	public Calendar getHorarioSubstituicao() {
		return horarioSubstituicao;
	}

	public void setHorarioSubstituicao(Calendar horarioSubstituicao) {
		this.horarioSubstituicao = horarioSubstituicao;
	}

	public Calendar getDataReposicao() {
		return dataReposicao;
	}

	public void setDataReposicao(Calendar dataReposicao) {
		this.dataReposicao = dataReposicao;
	}

	public Calendar getHorarioReposicao() {
		return horarioReposicao;
	}

	public void setHorarioReposicao(Calendar horarioReposicao) {
		this.horarioReposicao = horarioReposicao;
	}

	public Turma getIdTurma() {
		return turma;
	}

	public void setIdTurma(Turma turma) {
		this.turma = turma;
	}

	public FichaSubstituicao getIdFichaSubstituicao() {
		return fichaSubstituicao;
	}

	public void setIdFichaSubstituicao(FichaSubstituicao fichaSubstituicao) {
		this.fichaSubstituicao = fichaSubstituicao;
	}

	public Servidor getIdSubstituto() {
		return substituto;
	}

	public void setIdSubstituto(Servidor substituto) {
		this.substituto = substituto;
	}

	public QuadroDocente getIdDisciplinaSolicitante() {
		return disciplinaSolicitante;
	}

	public void setIdDisciplinaSolicitante(QuadroDocente disciplinaSolicitante) {
		this.disciplinaSolicitante = disciplinaSolicitante;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public FichaSubstituicao getFichaSubstituicao() {
		return fichaSubstituicao;
	}

	public void setFichaSubstituicao(FichaSubstituicao fichaSubstituicao) {
		this.fichaSubstituicao = fichaSubstituicao;
	}

	public Servidor getSubstituto() {
		return substituto;
	}

	public void setSubstituto(Servidor substituto) {
		this.substituto = substituto;
	}

	public QuadroDocente getDisciplinaSolicitante() {
		return disciplinaSolicitante;
	}

	public void setDisciplinaSolicitante(QuadroDocente disciplinaSolicitante) {
		this.disciplinaSolicitante = disciplinaSolicitante;
	}

}
