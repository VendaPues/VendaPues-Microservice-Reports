package org.venda.pues.reportapi.controller;

import models.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.venda.pues.reportapi.request.ReportRequest;
import org.venda.pues.reportapi.service.ReportingServices;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
public class ReportingController {

    private final ReportingServices reportingServices;

    public ReportingController(@Autowired ReportingServices reportingServices) {
        this.reportingServices = reportingServices;
    }

    @GetMapping("/sold-out")
    public ResponseEntity<?> getSoldOutProducts() {
        List<ProductDocument> result = reportingServices.getSoldOutProducts();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/products-popularity")
    public ResponseEntity<?> getProductsPopularity(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(this.reportingServices.getProductsPopularity(reportRequest.getInitDate(), reportRequest.getEndDate()));
    }

    @PostMapping("/sales")
    public ResponseEntity<?> getSales(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportingServices.getSalesPerTimePeriod(reportRequest.getInitDate(), reportRequest.getEndDate()));
    }
}
