package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.NotFoundException;
import com.mintic.genericstore.model.Sale;
import com.mintic.genericstore.model.User;
import com.mintic.genericstore.repository.SaleRepository;
import com.mintic.genericstore.repository.UserRepository;
import com.mintic.genericstore.service.SaleService;
import com.mintic.genericstore.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service("SaleServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    @Override
    public List<SaleResponseDTO> getSales() {
        List<Sale> sales = saleRepository.findAll();

        if (sales.isEmpty()) {
            log.warn(NO_SALE_DETAILS_FOUND);
            return Collections.EMPTY_LIST;
        }

        return sales.stream()
                .map((sale)-> MapperUtil.mapToDTO(sale, SaleResponseDTO.class))
                .toList();
    }

    @Override
    public SaleResponseDTO getSaleBySaleCode(Long saleCode) {
        Sale sale = findSaleByCodeOrThrow(saleCode);
        return MapperUtil.mapToDTO(sale, SaleResponseDTO.class);
    }

    @Override
    @Transactional
    public SaleResponseDTO saveSale(SaleRequestDTO request) {
        User user = findUserOrThrow(request.getUserId());

        Sale savedSale = getSavedSale(request, user);
        return MapperUtil.mapToDTO(savedSale, SaleResponseDTO.class);
    }

    private Sale getSavedSale(SaleRequestDTO request, User user) {
        Sale sale = MapperUtil.mapToDTO(request, Sale.class);
        sale.setUser(user);
        sale.setSaleId(generateSaleCode());

        return saleRepository.save(sale);
    }

    @Override
    @Transactional
    public SaleResponseDTO deleteSale(Long saleCode) {
        Sale sale = findSaleByCodeOrThrow(saleCode);
        saleRepository.deleteBySaleId(saleCode);
        return MapperUtil.mapToDTO(sale, SaleResponseDTO.class);
    }

    @Override
    @Transactional
    public SaleResponseDTO updateSale(SaleRequestDTO request) {
        User user = findUserOrThrow(request.getUserId());

        Sale updatedSale = getSale(request, user);
        return MapperUtil.mapToDTO(updatedSale, SaleResponseDTO.class);
    }

    private Sale getSale(SaleRequestDTO request, User user) {
        Sale sale = MapperUtil.mapToDTO(request, Sale.class);
        sale.setUser(user);
        return saleRepository.save(sale);
    }

    private Sale findSaleByCodeOrThrow(Long saleCode) {
        return saleRepository.findBySaleId(saleCode)
                .orElseThrow(() -> {
                    log.error(SALE_NOT_FOUND_WITH, saleCode);
                    return new NotFoundException(SALE_NOT_FOUND);
                });
    }

    private User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(USER_NOT_FOUND_WITH, userId);
                    return new NotFoundException(USER_NOT_FOUND);
                });
    }

    private Long generateSaleCode() {
        return saleRepository.count() + 1;
    }
}
