package com.br.cpfbackend.component;

import com.br.cpfbackend.entity.ClientEntity;
import com.br.cpfbackend.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQMessageHandlerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ReportComponent reportComponent;

    @InjectMocks
    private RabbitMQMessageHandler messageHandler;

    @Test
    public void testHandleNewCPFMessage() {
        ClientEntity clientEntity = new ClientEntity();
        UUID id = UUID.randomUUID();
        clientEntity.setId(id);
        messageHandler.handleNewCPFMessage(clientEntity);
        verify(clientService, times(1)).save(clientEntity);
    }

    @Test
    public void testHandleDuplicatedCpfMessage() {
        String cpf = "061.185.855-71";
        messageHandler.handleDuplicatedCpfMessage(cpf);
        verify(clientService, times(1)).checkCpf(cpf);
    }

    @Test
    public void testHandleGenerateReportMessage() {
        messageHandler.handleGenerateReportMessage();
        verify(reportComponent, times(1)).generateCsv();
    }
}
