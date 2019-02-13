/**
 * OrderComponent.java
 *
 * robgion
 * www.2clever.it
 * 
 * 07 giu 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.entities;

/**
 * @author robgion
 *
 */
public class OrderComponent {

	private Long idOrdine;
	private Integer idCliente;
	
	public OrderComponent(Long idOrdine, Integer idCliente) {
		this.idOrdine = idOrdine;
		this.idCliente = idCliente;
	}
	public Long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Long idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
