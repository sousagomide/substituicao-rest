package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;
import java.util.Calendar;

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
