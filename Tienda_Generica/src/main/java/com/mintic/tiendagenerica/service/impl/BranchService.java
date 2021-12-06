package com.mintic.tiendagenerica.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mintic.tiendagenerica.dto.request.BranchRequestDTO;
import com.mintic.tiendagenerica.dto.response.BranchResponseDTO;
import com.mintic.tiendagenerica.dto.response.SaleDetailResponseDTO;
import com.mintic.tiendagenerica.exception.TiendaGenericaException;
import com.mintic.tiendagenerica.model.Branch;
import com.mintic.tiendagenerica.repository.IBranchRepository;
import com.mintic.tiendagenerica.repository.ISaleDetailRepository;
import com.mintic.tiendagenerica.service.IBranchService;

@Service
public class BranchService implements IBranchService {

	private IBranchRepository iBranchRepository;
	private ModelMapper modelMapper;
	private ISaleDetailRepository iSaleDetailRepository;

	public BranchService(IBranchRepository iBranchRepository, ModelMapper modelMapper,
			ISaleDetailRepository iSaleDetailRepository) {
		this.iBranchRepository = iBranchRepository;
		this.modelMapper = modelMapper;
		this.iSaleDetailRepository = iSaleDetailRepository;
	}

	@Override
	public BranchResponseDTO saveBranch(BranchRequestDTO branch) throws TiendaGenericaException {
		return this.modelMapper.map(iBranchRepository.save(this.modelMapper.map(branch, Branch.class)),
				BranchResponseDTO.class);
	}

	@Override
	public List<SaleDetailResponseDTO> getSalesByBranch(BranchRequestDTO branch) throws TiendaGenericaException {

		return iSaleDetailRepository.findAll().stream().filter(data -> {
			return data.getSucursal().getId() == this.modelMapper.map(branch, Branch.class).getId();
		}).map(data -> this.modelMapper.map(data, SaleDetailResponseDTO.class)).collect(Collectors.toList());

	}

}