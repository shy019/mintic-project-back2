package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class SupplierRequestDTO {
	@Id
	private long nitProveedor;

	@NotBlank
	@Size(max = 150)
	private String ciudadProveedor;

	@NotBlank
	@Size(max = 60)
	private String direccionProveedor;

	@NotBlank
	@Size(max = 30)
	private String nombreProveedor;

	@NotBlank
	@Size(max = 120)
	private String telefonoProveedor;

	public Long getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(Long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getCiudadProveedor() {
		return ciudadProveedor;
	}

	public void setCiudadProveedor(String ciudadProveedor) {
		this.ciudadProveedor = ciudadProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public SupplierRequestDTO(Long nitProveedor, @NotBlank @Size(max = 150) String ciudadProveedor,
			@NotBlank @Size(max = 50) String direccionProveedor, @NotBlank @Size(max = 20) String nombreProveedor,
			@NotBlank @Size(max = 120) String telefonoProveedor) {
		super();
		this.nitProveedor = nitProveedor;
		this.ciudadProveedor = ciudadProveedor;
		this.direccionProveedor = direccionProveedor;
		this.nombreProveedor = nombreProveedor;
		this.telefonoProveedor = telefonoProveedor;
	}

	public SupplierRequestDTO() {
		super();
	}

}
