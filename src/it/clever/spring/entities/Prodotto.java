package it.clever.spring.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the prodotti database table.
 * 
 */
@Entity
@Table(name="prodotti")
@NamedQuery(name="Prodotto.findAll", query="SELECT p FROM Prodotto p")
public class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_prodotto")
	private Long idProdotto;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="prezzo")
	private BigDecimal prezzo;

	@Column(name="version")
	private int version;


	public Prodotto() {
	}

	
	public Long getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(Long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "Prodotto [idProdotto=" + idProdotto + ", codice=" + codice + ", descrizione=" + descrizione
				+ ", prezzo=" + prezzo + "]";
	}

}