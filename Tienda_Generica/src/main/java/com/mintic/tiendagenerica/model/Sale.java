package com.mintic.tiendagenerica.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mintic.tiendagenerica.model.jwt.User;

@Document(collection = "ventas")
public class Sale {

	@Id
	private Long codigoVenta;

	@DBRef()
	private Client cedulaCliente;

	/*
	 * @DBRef() private Product codigoProducto;
	 */

	@DBRef()
	private User cedula;

	// private Double cantidad;

	// private Double valorUnitario;

	private Double valorVenta;

	private Double ivaVenta;

	private Double totalVenta;

	public Long getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(Long codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public Client getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(Client cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	/*
	 * public Double getCantidad() { return cantidad; }
	 * 
	 * public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
	 * 
	 * public Double getValorUnitario() { return valorUnitario; }
	 * 
	 * public void setValorUnitario(Double valorUnitario) { this.valorUnitario =
	 * valorUnitario; }
	 */

	public User getCedula() {
		return cedula;
	}

	public void setCedula(User cedula) {
		this.cedula = cedula;
	}

	public Double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(Double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public Double getIvaVenta() {
		return ivaVenta;
	}

	public void setIvaVenta(Double ivaVenta) {
		this.ivaVenta = ivaVenta;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Sale() {
		super();
	}

}
