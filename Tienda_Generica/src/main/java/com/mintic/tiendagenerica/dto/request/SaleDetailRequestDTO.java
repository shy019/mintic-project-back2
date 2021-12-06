package com.mintic.tiendagenerica.dto.request;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;

public class SaleDetailRequestDTO {

	@Id
	private long codigoDetalleVenta;

	private long cantidadProducto;

	@Valid
	private ProductRequestDTO codigoProducto;

	@Valid
	private SaleRequestDTO codigoVenta;

	private double valorTotal;

	private double valorVenta;

	@Valid
	private BranchRequestDTO sucursal;

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

	public ProductRequestDTO getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(ProductRequestDTO codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public SaleRequestDTO getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(SaleRequestDTO codigoVenta) {
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

	public BranchRequestDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(BranchRequestDTO sucursal) {
		this.sucursal = sucursal;
	}

	public SaleDetailRequestDTO() {
		super();
	}

}