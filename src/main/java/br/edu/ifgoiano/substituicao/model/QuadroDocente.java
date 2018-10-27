package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quadrodocente")
public class QuadroDocente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idmatrizdisciplina", nullable = false)
	private MatrizDisciplina idMatrizDisciplina;

	@ManyToOne
	@JoinColumn(name = "idservidor", nullable = false)
	private Servidor idServidor;

	public QuadroDocente(Long id, MatrizDisciplina idMatrizDisciplina, Servidor idServidor) {
		this.id = id;
		this.idMatrizDisciplina = idMatrizDisciplina;
		this.idServidor = idServidor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatrizDisciplina getIdMatrizDisciplina() {
		return idMatrizDisciplina;
	}

	public void setIdMatrizDisciplina(MatrizDisciplina idMatrizDisciplina) {
		this.idMatrizDisciplina = idMatrizDisciplina;
	}

	public Servidor getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(Servidor idServidor) {
		this.idServidor = idServidor;
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
		QuadroDocente other = (QuadroDocente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
