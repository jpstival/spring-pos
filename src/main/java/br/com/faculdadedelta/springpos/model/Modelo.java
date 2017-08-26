package br.com.faculdadedelta.springpos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.faculdadedelta.springpos.model.type.Categoria;

@Entity
public class Modelo implements Serializable {

	private static final long serialVersionUID = -4711266343042279026L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="id_fabricante", referencedColumnName="id")
	@NotNull(message="Selecione o Fabricante.")
	private Fabricante fabricante;

	public Modelo() {

	}

	public Modelo(Long id, String descricao, Categoria categoria, Fabricante fabricante) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.fabricante = fabricante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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
		Modelo other = (Modelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
