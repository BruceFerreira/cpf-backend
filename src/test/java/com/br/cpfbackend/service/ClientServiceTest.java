package com.br.cpfbackend.service;

import com.br.cpfbackend.dto.ExistsDTO;
import com.br.cpfbackend.entity.ClientEntity;
import com.br.cpfbackend.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        ClientEntity clientEntity = new ClientEntity();
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        ClientEntity savedClient = clientService.save(clientEntity);

        assertNotNull(savedClient);
        verify(clientRepository, times(1)).save(clientEntity);
    }

    @Test
    void testCheckCpf() {
        String cpf = "061.185.855-71";
        when(clientRepository.existsByCpf(cpf)).thenReturn(true);

        Object result = clientService.checkCpf(cpf);

        assertTrue(result instanceof ExistsDTO);
        assertTrue(((ExistsDTO) result).isExists());
        verify(clientRepository, times(1)).existsByCpf(cpf);
    }
}
