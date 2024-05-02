package com.br.cpfbackend.service;

import com.br.cpfbackend.dto.ExistsDTO;
import com.br.cpfbackend.entity.ClientEntity;
import com.br.cpfbackend.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity save(ClientEntity clientEntity) {
        try {
            ClientEntity savedClient = clientRepository.save(clientEntity);
            log.info("Client saved successfully: {}", savedClient);
            return savedClient;
        } catch (Exception e) {
            log.error("An error occurred while handling new CPF message: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while handling new CPF message.", e);
        }
    }

    public Object checkCpf(String cpf) throws RuntimeException {
        try {
            log.info("Received message to check CPF: {}", cpf);
            boolean exists = clientRepository.existsByCpf(cpf);
            log.info("CPF check result: {}", exists);
            return new ExistsDTO(exists);
        } catch (Exception e) {
            log.error("Error checking CPF: {}", e.getMessage(), e);
            throw new RuntimeException("Error checking CPF.", e);
        }
    }
}
