package com.br.cpfbackend.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientDTOTest {

    @Test
    public void testEqualsAndHashCode() {
        ClientDTO client1 = new ClientDTO();
        client1.setName("Bruce Assis Ferreira");
        client1.setPhone("4799999999");
        client1.setCpf("061.185.855-71");
        client1.setAddress("Rua Felipe Camarão");
        client1.setNumber("1111");
        client1.setComplement("Apt 1");
        client1.setZipCode("885426632");
        client1.setNeighborhood("Vila Nova");
        client1.setCity("Blumenau");
        client1.setState("Santa Catarina");

        ClientDTO client2 = new ClientDTO();
        client2.setName("Bruce Assis Ferreira");
        client2.setPhone("4799999999");
        client2.setCpf("061.185.855-71");
        client2.setAddress("Rua Felipe Camarão");
        client2.setNumber("1111");
        client2.setComplement("Apt 1");
        client2.setZipCode("885426632");
        client2.setNeighborhood("Vila Nova");
        client2.setCity("Blumenau");
        client2.setState("Santa Catarina");

        assertTrue(client1.equals(client2));
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    public void testToString() {
        ClientDTO client = new ClientDTO();
        client.setName("Bruce Assis Ferreira");
        client.setPhone("4799999999");
        client.setCpf("061.185.855-71");
        client.setAddress("Rua Felipe Camarão");
        client.setNumber("1111");
        client.setComplement("Apt 1");
        client.setZipCode("885426632");
        client.setNeighborhood("Vila Nova");
        client.setCity("Blumenau");
        client.setState("Santa Catarina");

        String expectedToString = "ClientDTO(name=Bruce Assis Ferreira, phone=4799999999, cpf=061.185.855-71, " +
                "address=Rua Felipe Camarão, number=1111, complement=Apt 1, zipCode=885426632, " +
                "neighborhood=Vila Nova, city=Blumenau, state=Santa Catarina)";
        assertEquals(expectedToString, client.toString());
    }
}
