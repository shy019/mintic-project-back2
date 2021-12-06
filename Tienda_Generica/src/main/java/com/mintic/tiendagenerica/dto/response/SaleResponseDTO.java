package com.mintic.tiendagenerica.dto.response;

public class SaleResponseDTO {

	private Long codigoVenta;
	private ClientResponseDTO cedulaCliente;
	private UserResponseDTO cedula;
	private Double valorVenta;
	private Double ivaVenta;
	private Double totalVenta;

	public Long getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(Long codigoVenta) {
		this.codigoVenta = codigoVenta;
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

	public ClientResponseDTO getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(ClientResponseDTO cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public UserResponseDTO getCedula() {
		return cedula;
	}

	public void setCedula(UserResponseDTO cedula) {
		this.cedula = cedula;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public SaleResponseDTO() {
		super();
	}

}