package com.mintic.tiendagenerica.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class ProductRequestDTO {

	@Id
	private Long codigoProducto;
	// código_producto BIGINT 20

	@NotBlank
	@Size(max = 50)
	private String nombreProducto;
	// nombre_producto VARCHAR 50

	@Valid
	private SupplierRequestDTO nitProveedor;
	// nitproveedor BIGINT 20

	private Double precioCompra;
	// precio_compra DOUBLE

	private Double ivaCompra;
	// ivacompra DOUBLE

	private Double precioVenta;
	// precio_venta DOUBLE

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
