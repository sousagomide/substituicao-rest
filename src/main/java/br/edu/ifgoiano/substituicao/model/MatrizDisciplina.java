package br.edu.ifgoiano.substituicao.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "matriz_disciplina")
public class MatrizDisciplina implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EmbeddedId
	private MatrizDisciplinaId matrizDisciplinaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMatriz")
	@JoinColumn(name = "idmatriz")
    private Matriz matriz;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDisciplina")
	@JoinColumn(name = "iddisciplina")
    private Disciplina disciplina;

	public MatrizDisciplina(Matriz matriz, Disciplina disciplina) {
		this.matriz = matriz;
		this.disciplina = disciplina;
		this.matrizDisciplinaId = new MatrizDisciplinaId(matriz.getId(), disciplina.getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatrizDisciplinaId getMatrizDisciplinaId() {
		return matrizDisciplinaId;
	}

	public void setMatrizDisciplinaId(MatrizDisciplinaId matrizDisciplinaId) {
		this.matrizDisciplinaId = matrizDisciplinaId;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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
