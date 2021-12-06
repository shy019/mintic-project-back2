package com.mintic.tiendagenerica.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "detalle_ventas")
public class SaleDetail {

	@Id
	private long codigoDetalleVenta;

	private long cantidadProducto;

	@DBRef()
	private Product codigoProducto;

	@DBRef()
	private Sale codigoVenta;

	private double valorTotal;

	private double valorVenta;

	@DBRef()
	private Branch sucursal;

	public Branch getSucursal() {
		return sucursal;
	}

	public void setSucursal(Branch sucursal) {
		this.sucursal = sucursal;
	}

	public long getCodigoDetalleVenta() {
		return codigoDetalleVenta;
	}

	public void setCodigoDetalleVenta(long codigoDetalleVenta) {
		this.codigoDetalleVenta = codigoDetalleVenta;
	}

	public long getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(long cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Product getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Product codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Sale getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(Sale codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public SaleDetail() {
		super();
	}

}