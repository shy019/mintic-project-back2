package com.mintic.tiendagenerica.dto.response;

public class ProductResponseDTO {

	private Long codigoProducto;
	private String nombreProducto;
	private SupplierResponseDTO nitProveedor;
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

	public SupplierResponseDTO getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(SupplierResponseDTO nitProveedor) {
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

	public ProductResponseDTO() {
		super();
	}

}
