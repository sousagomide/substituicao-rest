package br.edu.ifgoiano.substituicao.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "disciplina")
public class Disciplina extends BaseModel {

	private String nome;

	private Boolean ativo;

	@Column(name = "cargahoraria")
	private BigDecimal cargaHoraria;

	@OneToMany(mappedBy = "disciplina", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<MatrizDisciplina> matrizesDisciplinas;

	public Disciplina() {
		this.ativo = true;
	}

	public Disciplina(Long id, String nome, BigDecimal cargaHoraria) {
		this.id = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.ativo = true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(BigDecimal cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<MatrizDisciplina> getMatrizesDisciplinas() {
		return matrizesDisciplinas;
	}

	public void setMatrizesDisciplinas(List<MatrizDisciplina> matrizesDisciplinas) {
		this.matrizesDisciplinas = matrizesDisciplinas;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
