package br.com.faculdadedelta.springpos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Locacao implements Serializable {

	private static final long serialVersionUID = -7205756727911797982L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	//@NotNull(message = "O campo valor não pode ser vazio!")
	private BigDecimal valorTotal;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	//@NotBlank(message = "O campo data de locação não pode ser vazio!")
	private Date dataLocacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	//@NotBlank(message = "O campo data de devolução não pode ser vazio!")
	private Date dataDevolucao;
	
	@ManyToOne
	@JoinColumn(name="id_motorista", referencedColumnName="id")
	@NotNull(message = "Selecione o Motorista!")
	private Motorista motorista;

	@ManyToOne
	@JoinColumn(name="id_carro", referencedColumnName="id")
	@NotNull(message = "Selecione o Carro!")
	private Carro carro;

	public Locacao() {

	}

	public Locacao(Long id, BigDecimal valorTotal, Date dataLocacao, Date dataDevolucao, Motorista motorista,
			Carro carro) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataLocacao = dataLocacao;
		this.dataDevolucao = dataDevolucao;
		this.motorista = motorista;
		this.carro = carro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Locacao locacao) {
		long dias = (locacao.getDataDevolucao().getTime() -locacao.getDataLocacao().getTime()) + 3600000;
		dias = dias / 86400000L;
		BigDecimal days = new BigDecimal(dias);
		this.valorTotal = locacao.getCarro().getValorDaDiaria().multiply(days);
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
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
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
