package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class ProductAllRequestDTO {

	@Id
	private Long codigoProducto;

	@NotBlank(message = "Debe digitar el nombre del nombreProducto")
	@Size(max = 50)
	private String nombreProducto;

	@NotBlank(message = "Debe digitar el nombre del nitProveedor")
	private Long nitProveedor;

	@NotBlank(message = "Debe digitar el nombre del precioCompra")
	private Double precioCompra;

	@NotBlank(message = "Debe digitar el nombre del ivaCompra")
	private Double ivaCompra;

	@NotBlank(message = "Debe digitar el nombre del precioVenta")
	private Double precioVenta;

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(Long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getIvaCompra() {
		return ivaCompra;
	}

	public void setIvaCompra(Double ivaCompra) {
		this.ivaCompra = ivaCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public ProductAllRequestDTO() {
		super();
	}

}

