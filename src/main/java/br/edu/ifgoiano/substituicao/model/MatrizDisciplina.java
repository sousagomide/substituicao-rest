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
@Table(name = "matriz_disciplina")
public class MatrizDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idmatriz", nullable = false)
	private Matriz idMatriz;

	@ManyToOne
	@JoinColumn(name = "iddisciplina", nullable = false)
	private Disciplina idDisciplina;

	public MatrizDisciplina(Long id, Matriz idMatriz, Disciplina idDisciplina) {
		this.id = id;
		this.idMatriz = idMatriz;
		this.idDisciplina = idDisciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Matriz getIdMatriz() {
		return idMatriz;
	}

	public void setIdMatriz(Matriz idMatriz) {
		this.idMatriz = idMatriz;
	}

	public Disciplina getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Disciplina idDisciplina) {
		this.idDisciplina = idDisciplina;
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
		MatrizDisciplina other = (MatrizDisciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
