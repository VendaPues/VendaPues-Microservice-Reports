package org.venda.pues.reportapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.venda.pues.reportapi.dto.ReportRequestDto;

@RestController
@RequestMapping("/v1/report")
public class ReportingController {

    @PostMapping("/sold-out")
    public ResponseEntity<?> getSoldOutProducts(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportRequestDto);
    }

    @PostMapping("/popular-products")
    public ResponseEntity<?> getPopularProducts(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportRequestDto);
    }

    @PostMapping("/unpopular-products")
    public ResponseEntity<?> getUnpopularProducts(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportRequestDto);
    }

    @PostMapping("/sales")
    public ResponseEntity<?> getSales(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportRequestDto);
    }
}
