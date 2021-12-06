package com.mintic.tiendagenerica.dto.response;

import org.springframework.data.annotation.Id;

public class SaleDetailResponseDTO {

	@Id
	private long codigoDetalleVenta;

	private long cantidadProducto;

	private ProductResponseDTO codigoProducto;

	private SaleResponseDTO codigoVenta;

	private double valorTotal;

	private double valorVenta;

	private BranchResponseDTO sucursal;

	public BranchResponseDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(BranchResponseDTO sucursal) {
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

	public ProductResponseDTO getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(ProductResponseDTO codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public SaleResponseDTO getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(SaleResponseDTO codigoVenta) {
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

	public SaleDetailResponseDTO() {
		super();
	}

}