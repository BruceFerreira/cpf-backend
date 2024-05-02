package com.br.cpfbackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @MockBean
    private ClientRepository mockClientRepository;

    @Test
    public void testExistsByCpf_WhenCpfExists_ReturnsTrue() {
        String existingCpf = "061.185.855-71";
        when(mockClientRepository.existsByCpf(existingCpf)).thenReturn(true);
        boolean result = clientRepository.existsByCpf(existingCpf);
        assertThat(result).isTrue();
    }

    @Test
    public void testExistsByCpf_WhenCpfDoesNotExist_ReturnsFalse() {
        String nonExistingCpf = "123.456.789-00";
        when(mockClientRepository.existsByCpf(nonExistingCpf)).thenReturn(false);
        boolean result = clientRepository.existsByCpf(nonExistingCpf);
        assertThat(result).isFalse();
    }
}
