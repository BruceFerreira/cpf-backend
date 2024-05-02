package com.br.cpfbackend.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExistsDTOTest {

    @Test
    void testExistsDTOConstructor() {
        boolean exists = true;
        ExistsDTO existsDTO = new ExistsDTO(exists);
        assertNotNull(existsDTO);
        assertTrue(existsDTO.isExists());
    }

    @Test
    void testGetExists() {
        boolean exists = true;
        ExistsDTO existsDTO = new ExistsDTO(exists);
        boolean result = existsDTO.isExists();
        assertTrue(result);
    }

    @Test
    void testSetExists() {
        ExistsDTO existsDTO = new ExistsDTO(false);
        existsDTO.setExists(true);
        assertTrue(existsDTO.isExists());
    }
}
