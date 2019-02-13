package it.clever.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="company")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	@Column(name="extended_desc")
	private String extendedDesc;

	@Column(name="id_company")
	private int idCompany;

	private int version;

	//bi-directional many-to-one association to Utente
	@OneToMany(mappedBy="companyBean", fetch=FetchType.LAZY)
	@OrderBy(value="cognome")
	private List<Utente> utenti;

	public Company() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtendedDesc() {
		return this.extendedDesc;
	}

	public void setExtendedDesc(String extendedDesc) {
		this.extendedDesc = extendedDesc;
	}

	public int getIdCompany() {
		return this.idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Utente> getUtenti() {
		return this.utenti;
	}

	public void setUtentis(List<Utente> utentes) {
		this.utenti = utentes;
	}

}