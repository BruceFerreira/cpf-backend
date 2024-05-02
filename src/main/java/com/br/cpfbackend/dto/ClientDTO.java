package com.br.cpfbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class ClientDTO {

    private String name;
    private String phone;
    private String cpf;
    private String address;
    private String number;
    private String complement;
    private String zipCode;
    private String neighborhood;
    private String city;
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(name, clientDTO.name) &&
                Objects.equals(phone, clientDTO.phone) &&
                Objects.equals(cpf, clientDTO.cpf) &&
                Objects.equals(address, clientDTO.address) &&
                Objects.equals(number, clientDTO.number) &&
                Objects.equals(complement, clientDTO.complement) &&
                Objects.equals(zipCode, clientDTO.zipCode) &&
                Objects.equals(neighborhood, clientDTO.neighborhood) &&
                Objects.equals(city, clientDTO.city) &&
                Objects.equals(state, clientDTO.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, cpf, address, number, complement, zipCode, neighborhood, city, state);
    }

}
