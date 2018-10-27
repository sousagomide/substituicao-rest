package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatrizDisciplinaId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "idmatriz")
	private Long idMatriz;
	
	@Column(name = "iddisciplina")
	private Long idDisciplina;
	
	public MatrizDisciplinaId() {}

	public MatrizDisciplinaId(Long idMatriz, Long idDisciplina) {
		this.idMatriz = idMatriz;
		this.idDisciplina = idDisciplina;
	}

	public Long getIdMatriz() {
		return idMatriz;
	}

	public void setIdMatriz(Long idMatriz) {
		this.idMatriz = idMatriz;
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDisciplina == null) ? 0 : idDisciplina.hashCode());
		result = prime * result + ((idMatriz == null) ? 0 : idMatriz.hashCode());
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
		MatrizDisciplinaId other = (MatrizDisciplinaId) obj;
		if (idDisciplina == null) {
			if (other.idDisciplina != null)
				return false;
		} else if (!idDisciplina.equals(other.idDisciplina))
			return false;
		if (idMatriz == null) {
			if (other.idMatriz != null)
				return false;
		} else if (!idMatriz.equals(other.idMatriz))
			return false;
		return true;
	}
}
