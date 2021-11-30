package com.mintic.tiendagenerica.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")

public class Product {

	@Id
	private Long codigoProducto;
	// código_producto BIGINT 20

	@NotBlank
	@Size(max = 50)
	private String nombreProducto;
	// nombre_producto VARCHAR 50

	@DBRef()
	private Supplier nitProveedor;
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

	public Supplier getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(Supplier nitProveedor) {
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

	@Override
	public String toString() {
		return "Product [codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", nitProveedor="
				+ nitProveedor + ", precioCompra=" + precioCompra + ", ivaCompra=" + ivaCompra + ", precioVenta="
				+ precioVenta + "]";
	}

	public Product() {
		super();
	}
}