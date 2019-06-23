package br.edu.ifgoiano.substituicao.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ifgoiano.substituicao.model.abs.BaseModel;

@Entity
@Table(name = "perfil")
public class Perfil extends BaseModel {

	private String descricao;

	public Perfil() { }
	
	public Perfil(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
