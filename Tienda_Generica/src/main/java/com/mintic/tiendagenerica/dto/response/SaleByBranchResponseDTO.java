package com.mintic.tiendagenerica.dto.response;

public class SaleByBranchResponseDTO {

	private String Sucursal;
	private Long cantidadProductosVendidos;
	private Double valorTotalVenta;
	private Double ivaTotalVenta;
	private Double valorTotalMasIvaVenta;

	public String getSucursal() {
		return Sucursal;
	}

	public void setSucursal(String sucursal) {
		Sucursal = sucursal;
	}

	public Long getCantidadProductosVendidos() {
		return cantidadProductosVendidos;
	}

	public void setCantidadProductosVendidos(Long cantidadProductosVendidos) {
		this.cantidadProductosVendidos = cantidadProductosVendidos;
	}

	public Double getValorTotalVenta() {
		return valorTotalVenta;
	}

	public void setValorTotalVenta(Double valorTotalVenta) {
		this.valorTotalVenta = valorTotalVenta;
	}

	public Double getIvaTotalVenta() {
		return ivaTotalVenta;
	}

	public void setIvaTotalVenta(Double ivaTotalVenta) {
		this.ivaTotalVenta = ivaTotalVenta;
	}

	public Double getValorTotalMasIvaVenta() {
		return valorTotalMasIvaVenta;
	}

	public void setValorTotalMasIvaVenta(Double valorTotalMasIvaVenta) {
		this.valorTotalMasIvaVenta = valorTotalMasIvaVenta;
	}

	public SaleByBranchResponseDTO(String sucursal, Long cantidadProductosVendidos, Double valorTotalVenta,
			Double ivaTotalVenta, Double valorTotalMasIvaVenta) {
		super();
		Sucursal = sucursal;
		this.cantidadProductosVendidos = cantidadProductosVendidos;
		this.valorTotalVenta = valorTotalVenta;
		this.ivaTotalVenta = ivaTotalVenta;
		this.valorTotalMasIvaVenta = valorTotalMasIvaVenta;
	}

	public SaleByBranchResponseDTO() {
		super();
	}

}
