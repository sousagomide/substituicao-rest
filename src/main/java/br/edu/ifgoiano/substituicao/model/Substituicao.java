package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "substituicao")
public class Substituicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idturma")
	private Turma idTurma;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idfichasubstituicao")
	private FichaSubstituicao idFichaSubstituicao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idsubstituto")
	private Servidor idSubstituto;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "iddisciplinasolicitante")
	private QuadroDocente idDisciplinaSolicitante;

	public Substituicao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return idTurma;
	}

	public void setIdTurma(Turma idTurma) {
		this.idTurma = idTurma;
	}

	public FichaSubstituicao getIdFichaSubstituicao() {
		return idFichaSubstituicao;
	}

	public void setIdFichaSubstituicao(FichaSubstituicao idFichaSubstituicao) {
		this.idFichaSubstituicao = idFichaSubstituicao;
	}

	public Servidor getIdSubstituto() {
		return idSubstituto;
	}

	public void setIdSubstituto(Servidor idSubstituto) {
		this.idSubstituto = idSubstituto;
	}

	public QuadroDocente getIdDisciplinaSolicitante() {
		return idDisciplinaSolicitante;
	}

	public void setIdDisciplinaSolicitante(QuadroDocente idDisciplinaSolicitante) {
		this.idDisciplinaSolicitante = idDisciplinaSolicitante;
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
		Substituicao other = (Substituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
