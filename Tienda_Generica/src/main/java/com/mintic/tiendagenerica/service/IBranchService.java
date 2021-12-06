package com.mintic.tiendagenerica.service;

import java.util.List;

import com.mintic.tiendagenerica.dto.request.BranchRequestDTO;
import com.mintic.tiendagenerica.dto.response.BranchResponseDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;

public interface IBranchService {

	public BranchResponseDTO saveBranch(BranchRequestDTO branch) throws TiendaGenericaException;

	public List<SaleDetailResponseDTO> getSalesByBranch(BranchRequestDTO branch) throws TiendaGenericaException;
}
