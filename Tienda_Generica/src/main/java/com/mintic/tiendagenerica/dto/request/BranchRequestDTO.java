package com.mintic.tiendagenerica.dto.request;

import org.springframework.data.annotation.Id;

public class BranchRequestDTO {

	@Id
	private Long id;

	private String nombreSucursal;

	private String ciudad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
