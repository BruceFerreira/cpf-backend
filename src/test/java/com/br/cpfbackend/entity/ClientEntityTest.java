package com.br.cpfbackend.entity;

import com.br.cpfbackend.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientEntityTest {

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testClientEntity() {
        UUID id = UUID.randomUUID();
        ClientEntity client = new ClientEntity();
        client.setId(id);
        client.setName("Bruce Assis Ferreira");
        client.setPhone("4799999999");
        client.setCpf("061.185.855-71");
        client.setAddress("rua felipe camarao");
        client.setNumber("1111");
        client.setComplement("Apt 1");
        client.setZipCode("885426632");
        client.setNeighborhood("vila nova");
        client.setCity("Blumenau");
        client.setState("Santa Catarina");
        java.util.Date currentDate = new java.util.Date();
        client.setCreatedDate(currentDate);
        client.setLastModifiedDate(currentDate);

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        Optional<ClientEntity> optionalClient = clientRepository.findById(id);
        assertThat(optionalClient).isPresent();
        ClientEntity retrievedClient = optionalClient.get();
        assertThat(retrievedClient.getName()).isEqualTo("Bruce Assis Ferreira");
        assertThat(retrievedClient.getPhone()).isEqualTo("4799999999");
        assertThat(retrievedClient.getCpf()).isEqualTo("061.185.855-71");
        assertThat(retrievedClient.getAddress()).isEqualTo("rua felipe camarao");
        assertThat(retrievedClient.getNumber()).isEqualTo("1111");
        assertThat(retrievedClient.getComplement()).isEqualTo("Apt 1");
        assertThat(retrievedClient.getZipCode()).isEqualTo("885426632");
        assertThat(retrievedClient.getNeighborhood()).isEqualTo("vila nova");
        assertThat(retrievedClient.getCity()).isEqualTo("Blumenau");
        assertThat(retrievedClient.getState()).isEqualTo("Santa Catarina");
        assertThat(retrievedClient.getCreatedDate()).isEqualTo(currentDate);
        assertThat(retrievedClient.getLastModifiedDate()).isEqualTo(currentDate);
    }
}