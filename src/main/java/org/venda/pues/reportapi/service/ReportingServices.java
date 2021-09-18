package org.venda.pues.reportapi.service;

import models.ProductDocument;
import models.SaleDocument;
import org.springframework.stereotype.Service;
import org.venda.pues.reportapi.model.SalesSummary;
import org.venda.pues.reportapi.repository.ProductRepository;
import org.venda.pues.reportapi.repository.SaleRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.List;

@Service
public class ReportingServices {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public ReportingServices(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public List<ProductDocument> getSoldOutProducts() {
        return productRepository.findByStockEquals(0);
    }

    public List<ProductDocument> getPopularProducts() {
        //TODO: Make a ne collection with the count of the times each product is sold
        throw new NotImplementedException();
    }

    public List<ProductDocument> getUnpopularProducts() {
        //TODO: Make a ne collection with the count of the times each product is sold
        throw new NotImplementedException();
    }

    public SalesSummary getSalesPerTimePeriod(Date initDate, Date endDate) {
        List<SaleDocument> sales = saleRepository.findBySoldAtBetween(initDate, endDate);
        Double totalSalesAmount = totalSalesAmountCalculator(sales);
        Double totalSalesCost = totalSalesCostCalculator(sales);

        return new SalesSummary(totalSalesAmount, sales.size(), totalSalesCost, totalSalesAmount - totalSalesCost);
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
        for (SaleDocument sale: sales) {
            aggregate = totalProductsCostCalculator(sale.getProducts(), aggregate);
        }

        return aggregate;
    }

    private Double totalProductsCostCalculator(List<String> products, Double aggregate) {
        for (String product: products) {
            ProductDocument productDocument = productRepository.findById(product).orElse(null);
            if(productDocument != null) {
                aggregate += productDocument.getPrice();
            }
        }

        return aggregate;
    }
}
