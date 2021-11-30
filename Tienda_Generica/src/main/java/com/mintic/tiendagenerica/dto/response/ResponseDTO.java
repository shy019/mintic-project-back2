package com.mintic.tiendagenerica.dto.response;

import java.util.List;

public class ResponseDTO {

	private int cantidad;
	private String mensaje;
	private List<ProductResponseDTO> guardados;
	private List<ProductResponseDTO> noGuardados;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<ProductResponseDTO> getGuardados() {
		return guardados;
	}

	public void setGuardados(List<ProductResponseDTO> guardados) {
		this.guardados = guardados;
	}

	public List<ProductResponseDTO> getNoGuardados() {
		return noGuardados;
	}

	public void setNoGuardados(List<ProductResponseDTO> noGuardados) {
		this.noGuardados = noGuardados;
	}

	public ResponseDTO(int cantidad, String mensaje, List<ProductResponseDTO> guardados,
			List<ProductResponseDTO> noGuardados) {
		super();
		this.cantidad = cantidad;
		this.mensaje = mensaje;
		this.guardados = guardados;
		this.noGuardados = noGuardados;
	}

}
