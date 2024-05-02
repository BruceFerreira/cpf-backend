package com.br.cpfbackend.component;

import com.br.cpfbackend.entity.ClientEntity;
import com.br.cpfbackend.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageHandler {

    private final ClientService clientService;

    private final ReportComponent reportComponent;

    @RabbitListener(queues = "new-cpf-queue")
    public Object handleNewCPFMessage(@Payload ClientEntity clientEntity) {
        log.info("Received message from Queue new-cpf-queue : {}", clientEntity);
        return clientService.save(clientEntity);
    }

    @RabbitListener(queues = "duplicated-cpf-queue")
    public Object handleDuplicatedCpfMessage(String cpf)  {
        try {
            log.info("Received duplicated CPF message: {}", cpf);
            Object result = clientService.checkCpf(cpf);
            log.info("Processing of duplicated CPF message complete. Result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error handling duplicated CPF message: {}", e.getMessage(), e);
            throw new RuntimeException("Error handling duplicated CPF message.", e);
        }
    }

    @RabbitListener(queues = "generate-report-queue")
    public void handleGenerateReportMessage() {
        try {
            log.info("Received generate report message");
            reportComponent.generateCsv();
            log.info("Processing of generate report message complete");
        } catch (Exception e) {
            log.error("Error processing generate report message: {}", e.getMessage(), e);
        }
    }
}