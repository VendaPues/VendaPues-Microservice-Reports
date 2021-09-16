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

        //TODO: Calculate the cost of the products so the earnings can be calculated.
        return new SalesSummary(totalSalesAmount, sales.size(), 0.0, totalSalesAmount - 0.0);
    }

    private Double totalSalesAmountCalculator(List<SaleDocument> sales) {
        double total = 0;
        for (SaleDocument sale : sales) {
            total = total + sale.getAmount();
        }

        return total;
    }
}
