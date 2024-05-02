package com.br.cpfbackend.component;

import com.br.cpfbackend.entity.ClientEntity;
import com.br.cpfbackend.repository.ClientRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class ReportComponent {

    private final ClientRepository clientRepository;
    private static final String CSV_FILE_NAME = "cpf.csv";

    @Autowired
    public ReportComponent(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void generateCsv() {
        log.info("Starting CSV report generation");
        List<ClientEntity> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            log.warn("No clients found to export.");
            log.info("CSV report generation skipped because no clients were found.");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(CSV_FILE_NAME)) {
            StatefulBeanToCsv<ClientEntity> csvWriter = new StatefulBeanToCsvBuilder<ClientEntity>(fileWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(';')
                    .withOrderedResults(false)
                    .build();
            csvWriter.write(clients);
            log.info("CSV report generation completed successfully");
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.error("Error generating CSV file: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao gerar o arquivo CSV.", e);
        }
    }
}
