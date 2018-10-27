package br.edu.ifgoiano.substituicao.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifgoiano.substituicao.model.enumeration.StatusFichaSubstituicao;

@Entity
@Table(name = "fichasubstituicao")
public class FichaSubstituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String justificativa;
	
	@Enumerated(EnumType.STRING)
	private StatusFichaSubstituicao status;
	
	@Column(name = "dataparecer")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataParecer;

	public FichaSubstituicao(Long id, String justificativa, StatusFichaSubstituicao status, Calendar dataParecer) {
		this.id = id;
		this.justificativa = justificativa;
		this.status = status;
		this.dataParecer = dataParecer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public StatusFichaSubstituicao getStatus() {
		return status;
	}

	public void setStatus(StatusFichaSubstituicao status) {
		this.status = status;
	}

	public Calendar getDataParecer() {
		return dataParecer;
	}

	public void setDataParecer(Calendar dataParecer) {
		this.dataParecer = dataParecer;
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
		FichaSubstituicao other = (FichaSubstituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}