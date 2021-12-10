package org.venda.pues.reportapi.service;

import dto.ProductSaleDetailsDto;
import error.exception.NotFoundException;
import models.ProductDocument;
import models.SaleDocument;
import models.UserDocument;
import org.springframework.stereotype.Service;
import org.venda.pues.reportapi.model.SalesSummary;
import org.venda.pues.reportapi.repository.ProductRepository;
import org.venda.pues.reportapi.repository.SaleRepository;
import org.venda.pues.reportapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingServices {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    public ReportingServices(ProductRepository productRepository, SaleRepository saleRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
    }

    public List<ProductDocument> getSoldOutProducts(String userId) {
        UserDocument user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<ProductDocument> products = new ArrayList<>();
            for (String productId : user.getProducts()) {
                ProductDocument product = productRepository.findByIdEqualsAndStockEquals(productId, 0);
                if (product != null) {
                    products.add(productRepository.findByIdEqualsAndStockEquals(productId, 0));
                }
            }

            return products;
        }
        throw new NotFoundException("User not found");
    }

    public SalesSummary getSalesPerTimePeriod(String userId) {
        UserDocument user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<SaleDocument> sales = new ArrayList<>();
            for (String saleId: user.getSales()) {
                saleRepository.findById(saleId).ifPresent(sales::add);
            }
            Double totalSalesAmount = totalSalesAmountCalculator(sales);
            Double totalSalesCost = totalSalesCostCalculator(sales);

            return new SalesSummary(totalSalesAmount, sales.size(), totalSalesCost, totalSalesAmount - totalSalesCost);
        }
        throw new NotFoundException("User not found");
    }

    private Double totalSalesAmountCalculator(List<SaleDocument> sales) {
        double total = 0;
        for (SaleDocument sale : sales) {
            total = total + sale.getAmount();
        }

        return total;
    }

    private Double totalSalesCostCalculator(List<SaleDocument> sales) {
        double aggregate = 0;
        for (SaleDocument sale : sales) {
            for (ProductSaleDetailsDto saleDetails : sale.getProducts()) {
                ProductDocument product = productRepository.findById(saleDetails.getProductId()).orElse(null);
                if (product != null) {
                    aggregate += product.getPrice() * saleDetails.getQuantity();
                }
            }
        }

        return aggregate;
    }
}
