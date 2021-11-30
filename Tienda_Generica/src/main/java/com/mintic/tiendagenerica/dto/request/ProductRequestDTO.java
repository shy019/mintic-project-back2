package com.mintic.tiendagenerica.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class ProductRequestDTO {

	@Id
	private Long codigoProducto;

	@NotBlank
	@Size(max = 50)
	private String nombreProducto;

	@Valid
	private SupplierRequestDTO nitProveedor;

	private Double precioCompra;

	private Double ivaCompra;

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

	public SupplierRequestDTO getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(SupplierRequestDTO nitProveedor) {
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

	public ProductRequestDTO() {
		super();
	}

}
