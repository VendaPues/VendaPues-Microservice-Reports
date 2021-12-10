package org.venda.pues.reportapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.venda.pues.reportapi.service.ReportingServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/v1/report")
public class ReportingController {

    private final ReportingServices reportingServices;

    public ReportingController(@Autowired ReportingServices reportingServices) {
        this.reportingServices = reportingServices;
    }

    @GetMapping("/{userId}/sold-out")
    public ResponseEntity<?> getSoldOutProducts(@PathVariable String userId) {
        return ResponseEntity.ok(reportingServices.getSoldOutProducts(userId));
    }

    @GetMapping("/{userId}/sales")
    public ResponseEntity<?> getSales(@PathVariable String userId) {
        return ResponseEntity.ok(reportingServices.getSalesPerTimePeriod(userId));
    }
}
