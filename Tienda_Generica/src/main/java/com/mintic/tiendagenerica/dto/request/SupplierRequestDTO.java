package com.mintic.tiendagenerica.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@Validated
public class SupplierRequestDTO {
	
	@Min(value = 1, message = "NIT no válido")
	@Max(value = 999999999, message = "NIT no válido")
	@Id
	private Long nitProveedor;

	@NotBlank(message = "Debe digitar ciudad donde se localiza el Proveedor")
	@Size(max = 150)
	private String ciudadProveedor;

	@NotBlank(message = "Debe digitar dirección del Proveedor")
	@Size(max = 60)
	private String direccionProveedor;

	@NotBlank(message = "Debe digitar nombre del Proveedor")
	@Size(max = 30) 
	private String nombreProveedor;

	@NotBlank(message = "Debe digitar teléfono de contacto del Proveedor - Ej.300-1234567")
	@Pattern(regexp = "[300-399]{3}-[0-9]{7}", message = "El número digitado no es correcto Ej.300-1234567")
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
