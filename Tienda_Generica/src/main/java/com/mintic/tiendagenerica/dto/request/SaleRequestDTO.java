package com.mintic.tiendagenerica.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import com.mintic.tiendagenerica.model.Client;

@Validated
public class SaleRequestDTO {

	@Id
	@NotNull(message = "El codigo de venta es requerido")
	@Min(value = 1, message = "Codigo de venta no válida")
	@Max(value = 9999999999L, message = "Codigo de venta no válida")
	private Long codigoVenta;

	@Valid
	private Client cedulaCliente;

	private Long cedulaUsuario;

	@NotNull(message = "Debe digitar el valor de venta del producto")
	private Double valorVenta;

	@NotNull(message = "Debe digitar el valor del IVA")
	private Double ivaVenta;

	@NotNull(message = "Debe digitar el valor total de venta")
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

	public Long getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(Long cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
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

	public SaleRequestDTO() {
		super();
	}

}