package com.br.cpfbackend.controller;

import com.br.cpfbackend.component.RabbitMQProducer;
import com.br.cpfbackend.component.ReportComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@Slf4j
public class ReportController {

    private final ReportComponent reportService;

    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public ReportController(ReportComponent reportService, RabbitMQProducer rabbitMQProducer) {
        this.reportService = reportService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateCsv() {
        try {
            log.info("Received request to generate CSV report");
            rabbitMQProducer.sendMessageAsync("Generate CSV","report-exchange","generate-report");
            log.info("CSV report generation request sent to RabbitMQ");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("CSV generation request accepted and processing started");
        } catch (Exception e) {
            log.error("Error generating CSV report: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate CSV report: " + e.getMessage());
        }
    }

}
