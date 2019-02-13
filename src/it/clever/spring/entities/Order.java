package it.clever.spring.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ordini database table.
 * 
 */
@Entity
@Table(name="ordini")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_ordine")
	private Long idOrdine;

	private BigDecimal ammontare;

	@Temporal(TemporalType.DATE)
	@Column(name="data_ordine")
	private Date dataOrdine;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Customer customer;

	private int quantita;

	@Version
	private int version;

	//bi-directional many-to-one association to Prodotto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_prodotto")
	private Prodotto prodotti1;


	public Order() {
	}

	public Long getIdOrdine() {
		return this.idOrdine;
	}

	public void setIdOrdine(Long idOrdine) {
		this.idOrdine = idOrdine;
	}

	public BigDecimal getAmmontare() {
		return this.ammontare;
	}

	public void setAmmontare(BigDecimal ammontare) {
		this.ammontare = ammontare;
	}

	public Date getDataOrdine() {
		return this.dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Customer getCliente() {
		return customer;
	}

	public void setCliente(Customer customer) {
		this.customer = customer;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Prodotto getProdotti1() {
		return this.prodotti1;
	}

	public void setProdotti1(Prodotto prodotti1) {
		this.prodotti1 = prodotti1;
	}

	@PrePersist
	public void prePersist() {
		System.out.println("@PrePersist --> Order");
	}
	
	@PostPersist
	public void postPersist() {
		System.out.println("@PostPersist --> Order");
	}

	@Override
	public String toString() {
		return "Order [idOrdine=" + idOrdine + ", ammontare=" + ammontare +  ", quantita=" + quantita + ", dataOrdine=" + dataOrdine + "]";
	}
	
	
}