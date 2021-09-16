package org.venda.pues.reportapi.model;

public class SalesSummary {

    Double totalSaleAmount;

    int totalSales;

    Double totalProductsCost;

    Double earnings;

    public SalesSummary(Double totalSaleAmount, int totalSales, Double totalProductsCost, Double earnings) {
        this.totalSaleAmount = totalSaleAmount;
        this.totalSales = totalSales;
        this.totalProductsCost = totalProductsCost;
        this.earnings = earnings;
    }

    public Double getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public Double getTotalProductsCost() {
        return totalProductsCost;
    }

    public Double getEarnings() {
        return earnings;
    }
}
