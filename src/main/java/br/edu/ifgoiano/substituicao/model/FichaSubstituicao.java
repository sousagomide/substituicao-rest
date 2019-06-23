package br.edu.ifgoiano.substituicao.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;
import br.edu.ifgoiano.substituicao.model.enumeration.StatusFichaSubstituicao;

@Entity
@Table(name = "fichasubstituicao")
public class FichaSubstituicao extends BaseModel {

	private String justificativa;

	@Enumerated(EnumType.STRING)
	private StatusFichaSubstituicao status;

	@Column(name = "dataparecer")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataParecer;

	@ManyToOne
	@JoinColumn(name = "idservidor")
	private Servidor servidor;

	public FichaSubstituicao() { }
	
	public FichaSubstituicao(Long id, String justificativa, StatusFichaSubstituicao status, Calendar dataParecer) {
		this.id = id;
		this.justificativa = justificativa;
		this.status = status;
		this.dataParecer = dataParecer;
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

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
