package com.br.cpfbackend.controller;

import com.br.cpfbackend.component.RabbitMQProducer;
import com.br.cpfbackend.component.ReportComponent;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportControllerTest {

    @Mock
    private ReportComponent reportService;

    @Mock
    private RabbitMQProducer rabbitMQProducer;

    @InjectMocks
    private ReportController reportController;

    @Test
    void testGenerateCsv() {
        doNothing().when(rabbitMQProducer).sendMessageAsync("Generate CSV","report-exchange","generate-report");

        ResponseEntity<String> response = reportController.generateCsv();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("CSV generation request accepted and processing started", response.getBody());
        verify(rabbitMQProducer, times(1)).sendMessageAsync("Generate CSV","report-exchange","generate-report");
    }
}
