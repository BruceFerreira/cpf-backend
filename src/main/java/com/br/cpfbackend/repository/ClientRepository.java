package com.br.cpfbackend.repository;

import com.br.cpfbackend.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository  extends JpaRepository<ClientEntity, UUID> {
    boolean existsByCpf(String cpf);
}
