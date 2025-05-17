package com.mintic.genericstore.utils;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchSalesCalculator {

    public SaleByBranchResponseDTO calculateSalesSummary(List<SaleDetailResponseDTO> sales, BranchRequestDTO branchRequest) {
        double totalTax = sales.stream()
                .mapToDouble(s -> s.getSaleCode().getSaleTax())
                .sum();

        long totalProductsSold = sales.stream()
                .mapToLong(SaleDetailResponseDTO::getProductQuantity)
                .sum();

        double totalSaleValue = sales.stream()
                .mapToDouble(SaleDetailResponseDTO::getTotalValue)
                .sum();

        double totalSaleValueWithTax = sales.stream()
                .mapToDouble(SaleDetailResponseDTO::getSaleValue)
                .sum();

        SaleByBranchResponseDTO response = new SaleByBranchResponseDTO();
        response.setBranch(branchRequest.getBranchName());
        response.setTotalSaleTax(totalTax);
        response.setSoldProductsQuantity(totalProductsSold);
        response.setTotalSaleValue(totalSaleValue);
        response.setTotalSaleWithTax(totalSaleValueWithTax);

        return response;
    }
}
