package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "quadrodocente")
public class QuadroDocente extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "idmatrizdisciplina", nullable = false)
	private MatrizDisciplina matrizDisciplina;

	@ManyToOne
	@JoinColumn(name = "idservidor", nullable = false)
	private Servidor servidor;

	public QuadroDocente() { }
	
	public QuadroDocente(Long id, MatrizDisciplina matrizDisciplina, Servidor servidor) {
		this.id = id;
		this.matrizDisciplina = matrizDisciplina;
		this.servidor = servidor;
	}

	public MatrizDisciplina getMatrizDisciplina() {
		return matrizDisciplina;
	}

	public void setMatrizDisciplina(MatrizDisciplina matrizDisciplina) {
		this.matrizDisciplina = matrizDisciplina;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
