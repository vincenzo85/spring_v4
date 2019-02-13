package it.clever.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * The persistent class for the clienti database table.
 * 
 */
@Entity
@Table(name="clienti")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cliente")
	private Long idCliente;

	@Column(name="codice")
	private String codice;

	@Column(name="piva")
	private String piva;

	@Column(name="rag_soc")
	private String ragSoc;

	@Column(name="num_orders", columnDefinition="default 0")
	private Integer numOrders;
	
	@Version
	private int version;

	public Customer() {
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getPiva() {
		return this.piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getRagSoc() {
		return this.ragSoc;
	}

	public void setRagSoc(String ragSoc) {
		this.ragSoc = ragSoc;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getNumOrders() {
		return numOrders;
	}

	public void setNumOrders(Integer numOrders) {
		this.numOrders = numOrders;
	}
	
	@Override
	public String toString() {
		return "Customer [idCliente=" + idCliente + ", codice=" + codice + ", piva=" + piva + ", ragSoc=" + ragSoc + "]";
	}
	
}