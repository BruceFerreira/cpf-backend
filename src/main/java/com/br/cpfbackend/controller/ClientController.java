package com.br.cpfbackend.controller;

import com.br.cpfbackend.component.RabbitMQProducer;
import com.br.cpfbackend.entity.ClientEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientController {
    private final RabbitMQProducer rabbitMQProducer;

    public ClientController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping()
    public ResponseEntity<Object> saveClient(@RequestBody ClientEntity clientEntity) {
        try {
            log.info("Received request to save client: {}", clientEntity);
            Object receive = rabbitMQProducer.sendMessage(clientEntity, "cpf-exchange", "new-cpf");
            log.info("Received return from RabbitMQ after saving client: {}", receive);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(receive);
        } catch (Exception e) {
            log.error("An error occurred while saving client: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving client.");
        }
    }

    @GetMapping("/checkCpf/{cpf}")
    public ResponseEntity<Object> checkIfCpfExists(@PathVariable String cpf) {
        try {
            log.info("Received request to check if CPF exists: {}", cpf);
            Object receive = rabbitMQProducer.sendMessage(cpf, "cpf-exchange", "duplicated-cpf");
            log.info("Checked if CPF exists. Result: {}", receive);
            return ResponseEntity.status(HttpStatus.OK).body(receive);
        } catch (Exception e) {
            log.error("Error checking if CPF exists: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking if CPF exists.");
        }
    }
}