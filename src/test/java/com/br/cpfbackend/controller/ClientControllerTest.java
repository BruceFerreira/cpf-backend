package com.br.cpfbackend.controller;

import com.br.cpfbackend.component.RabbitMQProducer;
import com.br.cpfbackend.entity.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientControllerTest {

    @Mock
    private RabbitMQProducer rabbitMQProducer;

    @InjectMocks
    private ClientController clientController;

    private ClientEntity clientEntity;

    @BeforeEach
    void setUp() {
        clientEntity = new ClientEntity();
        clientEntity.setName("Bruce Assis Ferreira");
        clientEntity.setPhone("4799999999");
        clientEntity.setCpf("061.185.855-71");
        clientEntity.setAddress("rua felipe camarao");
        clientEntity.setNumber("1111");
        clientEntity.setComplement("Apt 1");
        clientEntity.setZipCode("885426632");
        clientEntity.setNeighborhood("vila nova");
        clientEntity.setCity("Blumenau");
        clientEntity.setState("Santa Catarina");
    }

    @Test
    void testSaveClient() {
        when(rabbitMQProducer.sendMessage(clientEntity, "cpf-exchange", "new-cpf")).thenReturn("Client saved successfully");

        ResponseEntity<Object> response = clientController.saveClient(clientEntity);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Client saved successfully", response.getBody());
        verify(rabbitMQProducer, times(1)).sendMessage(clientEntity, "cpf-exchange", "new-cpf");
    }

    @Test
    void testCheckIfCpfExists() {
        String cpf = "061.185.855-71";
        when(rabbitMQProducer.sendMessage(cpf, "cpf-exchange", "duplicated-cpf")).thenReturn("CPF already exists");

        ResponseEntity<Object> response = clientController.checkIfCpfExists(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CPF already exists", response.getBody());
        verify(rabbitMQProducer, times(1)).sendMessage(cpf, "cpf-exchange", "duplicated-cpf");
    }
}
